import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule, RoutingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { CapstoreService } from './service/capstore.service';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmEqualValidatorDirective } from './Shared/confirm-equal-validator.directive';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { VerficationComponent } from './signup/verification.component';
import { HomeComponent } from './home/home.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ProductPageComponent } from './product-page/product-page.component';
import { VerifyMerchantComponent } from './verify-merchant/verify-merchant.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { FilterComponent } from './product-filter/filter.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { CategoryPageComponent } from './category-page/category-page.component';
@NgModule({
  declarations: [
    AppComponent,
    ConfirmEqualValidatorDirective,
    SignupComponent,
    LoginComponent,
    VerficationComponent,
    HomeComponent,
    HomePageComponent,
    ProductPageComponent,
    VerifyMerchantComponent,
    ForgotPasswordComponent,
    FilterComponent,
    ChangePasswordComponent,
    CategoryPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [CapstoreService],
  bootstrap: [AppComponent],
})
export class AppModule {}
