package com.example.fiaoRDITSC.Models;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class BaseModel  extends ViewModel implements Serializable {

        private String Id;

        public String getId() {
                return this.Id;
        }
        public void setId(String id) {
                this.Id = id;
        }



}
