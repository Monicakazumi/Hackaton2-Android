package com.example.hachathon2.NavFragments;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hachathon2.models.Usuario;

public class SharedPreManager {
   private static String SHARED_PREF_NAME="thecodingshef";
   private SharedPreferences sharedPreferences;
   Context context;
   private SharedPreferences.Editor editor;


    public SharedPreManager(Context context) {
        this.context = context;
    }

    public void SalvarUser(Usuario usuario) {
    sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
    editor=sharedPreferences.edit();
    editor.putInt("id", usuario.getId());
    editor.putString("usuario", usuario.getUsuario());
    editor.putString("senha", usuario.getSenha());
    editor.putBoolean("isLoggedIn", true);
    editor.apply();
    }
    public  boolean isUserLoggedIn() {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

     public Usuario getUser(){
         sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
         return new Usuario(sharedPreferences.getInt("id",-1),
                 sharedPreferences.getString("usuario",null),
                 sharedPreferences.getString("senha",null));
     }
     public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
     }
}
