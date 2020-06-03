import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../Model/Product.model';
import { User } from '../Model/User.model';
@Injectable({
  providedIn: 'root',
})
export class CapstoreService {
  custDetails: User[] = [];
  merchDetails: User[] = [];
  email;
  private baseUrl = 'http://localhost:8054/Billing-App';
  private loggedInStatus = JSON.parse(
    localStorage.getItem('loggedIn') || 'false'
  );
  constructor(private http: HttpClient) {}
  public registerCustomer(customer: Object): Observable<any> {
    return this.http.post(this.baseUrl + '/registerCustomer', customer);
  }
  public registerMerchant(merchant: Object): Observable<any> {
    return this.http.post(this.baseUrl + '/registerMerchant', merchant);
  }

  public login(email, password, role): Observable<any> {
    return this.http.post(this.baseUrl + '/login', [email, password, role]);
  }

  public getUser(tk): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/confirm-account?token=' + tk);
  }

  getCategory(category) {
    return this.http.get<Product[]>(
      this.baseUrl + '/productCategory/' + category
    );
  }
  getAllProducts() {
    return this.http.get<Product[]>(this.baseUrl + '/allProducts');
  }
  getDiscount(category,discountPercent): Observable<any> {
    return this.http.get<Product[]>(
      this.baseUrl +'/discountCategory/' +category+'/'+ discountPercent
    );
  }
  getSearchProducts(category) {
    return this.http.get<Product[]>(
      this.baseUrl + '/searchProducts/' + category
    );
  }
  getProduct(id) {
    return this.http.get<Product>(
     this.baseUrl+'/product/'+id
      
    );
  }

  get isLoggedIn() {
    return JSON.parse(
      localStorage.getItem('loggedIn') || this.loggedInStatus.toString()
    );
  }
  setLoggedIn(value: boolean) {
    this.loggedInStatus = value;
    localStorage.setItem('loggedIn', 'true');
  }
  setCurrentCustomer(user) {
    localStorage.setItem('customer', JSON.stringify(user));
  }
  getCurrentCustomer() {
    return localStorage.getItem('customer');
  }
  setCurrentMerchant(user) {
    localStorage.setItem('merchant', JSON.stringify(user));
  }
  getCurrentMerchant() {
    return localStorage.getItem('merchant');
  }
  getUserDetail() {
    return 'ram@gmail.com';
  }
  getSomeData() {
    return 'bhaak';
  }

  getMerchantForVerification(token: String): Observable<any> {
    return this.http.get(this.baseUrl + '/getMerchant?token=' + token);
  }

  getToken(token: string, action: string): Observable<any> {
    return this.http.get(
      this.baseUrl + '/generateToken?token=' + token + '&action=' + action
    );
  }

  forgotPassword(email: string): Observable<any> {
    return this.http.post(this.baseUrl + '/forgotPassword', email);
  }

  changePassword(
    currentPassword: string,
    newPassword: string,
    email: string
  ): Observable<any> {
    return this.http.post(this.baseUrl + '/changePassword', [
      currentPassword,
      newPassword,
      email,
    ]);
  }

  // sortFilter(filter: string): Observable<any> {
  //   return this.http.get(this.baseUrl + '/filter/' + filter);
  // }

  sortFilter(filter, category) {
    if (filter.localeCompare('Price-Low To High') == 0) {
      return this.http.get<Product[]>(this.baseUrl + '/' + category + '/Asc');
    }

    if (filter.localeCompare('Price-High to Low') == 0) {
      return this.http.get<Product[]>(this.baseUrl + '/' + category + '/Desc');
    }

    if (filter.localeCompare('Rating-Low To High') == 0) {
      return this.http.get<Product[]>(this.baseUrl + '/' + category + '/Rasc');
    }

    if (filter.localeCompare('Rating-High to Low') == 0) {
      return this.http.get<Product[]>(this.baseUrl + '/' + category + '/Rdesc');
    }
  }
}
