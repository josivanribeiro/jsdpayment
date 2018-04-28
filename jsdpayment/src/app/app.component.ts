import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from 'ionic-native';
import { LoginPage } from '../pages/login/login';
 
@Component({
  template: `<ion-nav [root]="rootPage"></ion-nav>`
})
export class jsdPayment {
  rootPage = LoginPage;
 
  constructor(platform: Platform) {
    platform.ready().then(() => {
      StatusBar.styleDefault();
    });
  }
}