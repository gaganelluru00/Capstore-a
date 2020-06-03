import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { VerficationComponent } from './signup/verification.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ProductPageComponent } from './product-page/product-page.component';
import { HomeComponent } from './home/home.component';
import { VerifyMerchantComponent } from './verify-merchant/verify-merchant.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { FilterComponent } from './product-filter/filter.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { CategoryPageComponent } from './category-page/category-page.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'verify', component: VerficationComponent },
  { path: 'homepage', component: HomePageComponent },
  { path: 'productpage/:id', component: ProductPageComponent },
  { path: 'home', component: HomeComponent },
  { path: 'verifyMerchant', component: VerifyMerchantComponent },
  { path: 'forgotPassword', component: ForgotPasswordComponent },
  //{ path: 'filterProducts', component: FilterComponent },
  { path: 'changePassword', component: ChangePasswordComponent },
  { path: 'categorypage/:category', component: CategoryPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
export const RoutingComponents = [
  LoginComponent,
  SignupComponent,
  VerficationComponent,
];
