package com.example.chatapplication.ui.Model

class User {
      var name:String?=null
      var email:String?=null
      var uid:String?=null

    constructor(){}
    constructor(name:String?,email:String?,uid:String?){
        this.name=name
        this.uid=uid
        this.email=email
    }
}