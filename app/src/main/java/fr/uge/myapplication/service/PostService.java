package fr.uge.myapplication.service;

import android.content.Context;

import java.util.List;

import fr.uge.myapplication.model.PC;
import fr.uge.myapplication.service.network.Httpservice;

public class PostService {

    Context ctx;
    String url ="http://192.168.1.114:8080/" ;

    public PostService(Context ctx) {
        this.ctx = ctx;
    }

    public List<PC> getallposts(){
        Httpservice http = Httpservice.getinstance();
        return null;

    }


}
