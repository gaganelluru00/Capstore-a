import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapstoreService } from '../service/capstore.service';
import { Product } from '../Model/Product.model';

@Component({
  selector: 'app-homepage',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  category: string = 'All Products';
  filter: string = '';
  searchProduct: string = '';
  categoryProducts: Product[];
  allProducts: Product[];
  products: Product[];
  columnDisplay: number = 3;
  noShow: boolean = false;
  discountPercent: string = '';

  constructor(
    private capstoreService: CapstoreService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.capstoreService.getAllProducts().subscribe((response) => {
      this.allProducts = response;

      this.products = this.removeDuplicates(
        this.allProducts,
        'productCategory'
      );
      console.log(this.products);
    });
  }

  getCategoryProducts(c) {
    this.category = c.target.innerHTML;

    this.noShow = true;
    this.capstoreService.getCategory(this.category).subscribe((response) => {
      this.categoryProducts = response;
    });
  }

  getDiscountProducts(c) {
    this.discountPercent = c.target.innerHTML;
    localStorage.setItem('category', this.category);
    this.noShow = true;
    this.capstoreService
      .getDiscount(localStorage.getItem('category'), this.discountPercent)
      .subscribe((response) => {
        this.categoryProducts = response;
      });
    console.log(this.categoryProducts);
  }

  applyFilters(selectedFilter) {
    this.filter = selectedFilter;
  }

  removeDuplicates(array, key) {
    let lookup = new Set();
    return array.filter((obj) => !lookup.has(obj[key]) && lookup.add(obj[key]));
  }

  search() {
   this.route.navigate(['categorypage',this.searchProduct]);
  }

  show() {
    alert('1');
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
}
