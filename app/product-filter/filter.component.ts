import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CapstoreService } from '../service/capstore.service';
import { Product } from '../Model/Product.model';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
})
export class FilterComponent implements OnInit {
  filter: string = '';
  allProducts: Product[];
  products: Product[];
  filterProducts: Product[];
  noShow: boolean = false;

  constructor(
    private capstoreService: CapstoreService,
    private route: Router
  ) {}

  ngOnInit(): void {}

  sortFilter(f) {
    this.filter = f.target.innerHTML;
    this.noShow = true;
    // this.capstoreService.sortFilter(this.filter).subscribe((response) => {
    //   this.filterProducts = response;
    // });
    // console.log(this.filterProducts);
  }
}
