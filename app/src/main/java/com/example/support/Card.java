package com.example.support;

public class Card {
        String name,address,phone;
        int id;
        public Card(String name,String address,String phone,int id){
            this.name=name;
            this.address=address;
            this.phone=phone;
            this.id=id;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public int getId() {
            return id;
        }
    }
