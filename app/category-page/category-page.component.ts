import { Component, OnInit } from '@angular/core';
import { CapstoreService } from '../service/capstore.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../Model/Product.model';

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.css'],
})
export class CategoryPageComponent implements OnInit {
  category: string;
  filter: string = '';
  searchProduct: string = '';
  categoryProducts: Product[];
  allProducts: Product[];
  products: Product[];
  columnDisplay: number = 3;
  noShow: boolean = true;
  filterShow = false;
  discountPercent: string = '';
  routeCategory: string;
  filterProducts: Product[];
  

  constructor(
    private capstoreService: CapstoreService,
    private route: Router,
    private ar: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.ar.paramMap.subscribe((params) => {
      this.routeCategory = params.get('category');
    });

    this.getCategoryProducts(this.routeCategory);
  }


  getDiscountProducts(c) {
    this.discountPercent = c.target.innerHTML;
    this.capstoreService
      .getDiscount(this.routeCategory,this.discountPercent)
      .subscribe((response) => {
        this.categoryProducts = response;
      });
    console.log(this.categoryProducts);
  }


  getCategoryProducts(c) {
    this.category = this.routeCategory;

    this.capstoreService.getCategory(this.category).subscribe((response) => {
      this.categoryProducts = response;
    });
  }

  sortFilter(f, c) {
    this.filter = f.target.innerHTML;
    this.category = c;
    this.noShow = false;
    this.filterShow = true;
    this.capstoreService
      .sortFilter(this.filter, this.category)
      .subscribe((response) => {
        this.categoryProducts = response;
      });
    console.log(this.filterProducts);
  }
  
  productPage(id){
    this.route.navigate(['productpage',id]);
  }


  search() {
    this.routeCategory=this.searchProduct;
    this.getCategoryProducts(this.routeCategory);
    
   }

  closePop() {
    document.getElementById('id01').style.display = 'none';
  }
  openPop() {
    document.getElementById('id01').style.display = 'block';
  }

  openNav() {
    document.getElementById('mySidebar').style.width = '250px';
    document.getElementById('main').style.marginLeft = '250px';
  }

  closeNav() {
    document.getElementById('mySidebar').style.width = '0';
    document.getElementById('main').style.marginLeft = '0';
  }
  homePage() {
    this.route.navigate(['homepage']);
  }
}
