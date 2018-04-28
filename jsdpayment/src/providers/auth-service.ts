import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';
 
export class User {
  name: string;
  email: string;
 
  constructor(name: string, email: string) {
    this.name = name;
    this.email = email;
  }
}
 
@Injectable()
export class AuthService {
  currentUser: User;
  //http: Http;
  public access: boolean = false;
  public result: string;
     
  constructor (public http: Http) {  	
  }
  
  public login(credentials) {
    if (credentials.email === null || credentials.password === null) {
      return Observable.throw("Please insert credentials");
    } else {      
    
      var url = 'http://www.josivansilva.com/jsdPaymentServices/services/loginService/'+ credentials.email + '/' + credentials.password;
	  this.http.get(url).map(res => res.json()).subscribe(data => {
	  	this.result = data.result;  		    	
	  });	
    
      if (this.result == "true") {
	  	this.access = true;
	  }
    
      return Observable.create(observer => {
              	
        this.currentUser = new User('Josivan', 'josivan@josivansilva.com');       	
    	        
        //let access = (credentials.password === "pass" && credentials.email === "email");        
        observer.next(this.access);
        observer.complete();
      });
    }
  }
 
  public register(credentials) {
    if (credentials.email === null || credentials.password === null) {
      return Observable.throw("Please insert credentials");
    } else {
      // At this point store the credentials to your backend!
      return Observable.create(observer => {
        observer.next(true);
        observer.complete();
      });
    }
  }
 
  public getUserInfo() : User {
    return this.currentUser;
  }
 
  public logout() {
    return Observable.create(observer => {
      this.currentUser = null;
      observer.next(true);
      observer.complete();
    });
  }
  
  handleError(error) {
  	console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }  
  
}