import { NgModule } from '@angular/core';
import { IonicApp, IonicModule } from 'ionic-angular';
import { jsdPayment } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { AuthService } from '../providers/auth-service';
import { RegisterPage } from '../pages/register/register';
 
@NgModule({
  declarations: [
    jsdPayment,
    HomePage,
    LoginPage,
    RegisterPage
  ],
  imports: [
    IonicModule.forRoot(jsdPayment)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    jsdPayment,
    HomePage,
    LoginPage,
    RegisterPage
  ],
  providers: [AuthService]
})
export class AppModule {}