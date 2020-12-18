import { Component, OnInit } from '@angular/core';
import { LocalStorageService } from '../../services/local-storage.service';
import { Router } from '@angular/router';
import { ComboboxModel } from '../../models/combobox-model';
import { CheckoutForm } from '../../models/checkoutForm';
import { CartModel } from '../../models/cartmodel';
import { CognitoService } from '../../services/cognito.service';
import { DynamodbService } from '../../services/dynamodb.service';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ValueConverter } from '@angular/compiler/src/render3/view/template';

declare const L: any;

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  state: ComboboxModel[] = [
  ];
  Agences: ComboboxModel[] = [
  ];
  checkout: CheckoutForm;
  cartIsEmptyBool: boolean = false;
  myCartArr: CartModel[] = [];
  grandTotal: string;
  showCongrats: boolean = false;
  errorMsgArr: string[] = [];
  myGridOptions: any;

  constructor(private localStor: LocalStorageService, private router: Router, private cognito: CognitoService, private dynamo: DynamodbService,private _httpClient: HttpClient) { }

  ngOnInit() {
    this._httpClient.get('http://localhost:8080/getAgences').subscribe(data => {
     let someArray=data;
     const mapped = Object.keys(data).map(key => ({value: key, text: data[key]}));
     
     for (let entry of mapped){
      //console.log(entry);
      this.state.push(entry);
      //console.log(this.state[0]);   
      //console.log(entry[0]);
    }
    });

    this._httpClient.get('http://localhost:8080/getPostes?ville=ST ETIENNE').subscribe(data => {
      let someArray=data;
      const mapped = Object.keys(data).map(key => ({value: key, text: data[key]}));
      
      for (let entry of mapped){
       console.log(entry["text"]);
       //value: CheckoutForm;
       console.log()
       this.Agences.push(entry);
       //console.log(this.state[0]);   
       //console.log(entry[0]);
     }
     });

    //console.log(this.getAgences());
    // if the cart is empty, I set the boolean to true, and display in html template cart is empty.
    if (this.localStor.cartDataArr.length == 0) {
      this.cartIsEmptyBool = true;
    }
    if (!navigator.geolocation) {
      console.log('location is not supported');
    }
    navigator.geolocation.getCurrentPosition((position) => {
      const coords = position.coords;
      const latLong = [coords.latitude, coords.longitude];



      console.log(
        `lat: ${position.coords.latitude}, lon: ${position.coords.longitude}`
      );
      let mymap = L.map('map').setView(latLong, 13);

      L.tileLayer(
        'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoiYWNocmFmYmVuaGFtb3UiLCJhIjoiY2tpc3kyZnJ3MHBneDJxc2NzbW9tZmRxdiJ9.4NzdG0WUPiqyxJg_5hZoyw',
        {
          attribution:
            'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
          maxZoom: 18,
          id: 'mapbox/streets-v11',
          tileSize: 512,
          zoomOffset: -1,
          accessToken: 'your.mapbox.access.token',
        }
      ).addTo(mymap);
/////
      let marker = L.marker(latLong).addTo(mymap);
      this._httpClient.get('http://localhost:8080/getPostes?ville=ST ETIENNE').subscribe(data => {
        let someArray=data;
        const mapped = Object.keys(data).map(key => ({value: key, text: data[key]}));
        for (let entry of mapped){
          console.log("##############")
         console.log(entry["text"]);
         console.log(entry["text"]["lat"]);
         let latLong2 =[entry["text"]["lat"],entry["text"]["lon"]]
         console.log(latLong2)
         console.log(latLong)
         L.marker(latLong2).addTo(latLong2);
         let popup = L.popup()
         .setLatLng(latLong2)
         .setContent('I am here')
         .openOn(mymap);
         //value: CheckoutForm;
         console.log()
         this.Agences.push(entry);
         //console.log(this.state[0]);   
         //console.log(entry[0]);
       }
       });

      marker.bindPopup('<b>you are here</b>').openPopup();

      let popup = L.popup()
        .setLatLng(latLong)
        .setContent('I am here')
        .openOn(mymap);
    });
    this.watchPosition();
    this.myCartArr = this.localStor.cartDataArr;
    this.calculateGrandTotal();

    this.cognito.arrDataChangeBehaviorSubj.subscribe((data) => {
      // I am binding this object to my html template. (2 way data binding)
      this.checkout = {
        name: this.cognito.myArray[2] + " " + this.cognito.myArray[3],
        email: this.cognito.myArray[0],
        street: "",
        city: "",
        states: "select",
        zip: ""

      }
    });
 
  }
  
  // This function gets 'todays' date and render it in the format MM/DD/YYYY
  purchaseDate(): string {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();
    var day;
    var month;
    var fullDate;
    //add 0 in front of date if necessary
    if (dd < 10) {
      day = '0' + dd
    }
    //add month in front of date if necessary
    if (mm < 10) {
      month = '0' + mm
    }
    fullDate = mm + '/' + dd + '/' + yyyy;
    return fullDate;
  }
  watchPosition()
  {
    let desLat = 0;
    let desLon = 0;
    let id = navigator.geolocation.watchPosition(
      (position) => {
        console.log(
          `lat: ${position.coords.latitude}, lon: ${position.coords.longitude}`
        );
        if (position.coords.latitude === desLat) {
          navigator.geolocation.clearWatch(id);
        }
      },
      (err) => {
        console.log(err);
      },
      {
        enableHighAccuracy: true,
        timeout: 5000,
        maximumAge: 0,
      }
    );
    
  }
  onSubmit({ value, valid }: { value: CheckoutForm, valid: boolean }) {
    this.emptyErrMsgArr();

    if (!valid) {


    }
    else {
      // setting var date equalt to the date (mm/dd/yyyy format) return by the function in string format.
      var date = this.purchaseDate();
      
      var shippingAddress = value.street + " " + value.city + " " + value.states + " " + value.zip;

      // function located in DynamoDB service. returns promise. its input are the cart information, user email, purchase date, and shipping address.
      this.dynamo.saveOrderHistory(this.myCartArr, this.cognito.myArray[0], date, shippingAddress, value.name).then(
        // success, then i show the congratulation message by setting boolean to true for few seconds, then i hide the message and reroute the user.
        (data) => {
          this.showCongrats = true;
          setTimeout(() => {
            this.showCongrats = false;
            // empty the cart array in severice localStorage
            this.localStor.emptyCart();
            // clear cart data from local browser local storage.
            localStorage.removeItem("data");
            // reroute them to order history
            this.router.navigate(['orderhistory']);

          }, 3000);

        },
        // error occurred, i pushed the message in to an array and display the message in the html template. in addition set bool that show congrats message to false.
        (err) => {
          this.errorMsgArr.push(err);
          this.showCongrats = false;
        }
      );


    }
  }

  getAgences() {
    this._httpClient.get('http://localhost:8080/getAgences').subscribe(data => {
      return(data);
    });
  }


 
  // this function addup the prices for each item purchased and display the total of all items combined
  calculateGrandTotal() {
    var temp = 0;
    for (var i = 0; i < this.myCartArr.length; i++) {
      temp = parseFloat(this.myCartArr[i].finalPrice) + temp;
    }
    this.grandTotal = temp.toString();
  }

  emptyErrMsgArr(){
    while(this.errorMsgArr.length > 0){
      this.errorMsgArr.pop();
    }
  }

}
