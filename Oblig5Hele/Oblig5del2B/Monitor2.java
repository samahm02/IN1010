import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.*;

public class Monitor2 {
    static SubsekvensRegister Register = new SubsekvensRegister();
    private static Lock laas = new ReentrantLock();

    public void SettInnHash(HashMap<String, Subsekvens> map){
        laas.lock();
        try{
            Register.leggTilArray(map);
        }finally{
            laas.unlock();
        } 

    }

    public void leggTilSubsekvens(HashMap<String, Subsekvens> map){
        laas.lock();
        try{
            Register.leggTilSubsekvens(map);
        }finally{
            laas.unlock();
        } 

    }
    public void leggTilSubsekvensPlass(int plass,HashMap<String, Subsekvens> map){
        laas.lock();
        try{
            Register.leggTilSubsekvensPlass( plass,map);
        }finally{
            laas.unlock();
        } 

    }

    public HashMap<String, Subsekvens> HentSubsekvens(int plass){
        laas.lock();
        try{
            return Register.hentSubsekvens(plass);
        }finally{
            laas.unlock();
        } 

    }

    public int Storrelse(){
        laas.lock();
        try{
            return Register.Storrelse();
        }finally{
            laas.unlock();
        } 
    }

    public void fjernFraArray(HashMap<String, Subsekvens> mapSomSkalFjernes){
        laas.lock();
        try{
            Register.fjernFraArray(mapSomSkalFjernes);
        }finally{
            laas.unlock();
        } 
    }

    public void leggTilArray(HashMap<String, Subsekvens> mapSomSkalLeggesTil){
        laas.lock();
        try{
            Register.leggTilArray(mapSomSkalLeggesTil); ;
        }finally{
            laas.unlock();
        } 
    }

    public void lesArray (){
        laas.lock();
        try{
            Register.lesArray();
        }finally{
            laas.unlock();
        } 
    }
    
    public void lesMap(HashMap<String, Subsekvens> mapet){
        laas.lock();
        try{
            Register.lesMap(mapet);
        }finally{
            laas.unlock();
        } 
    }

    public static HashMap<String, Subsekvens> samleMap(HashMap<String, Subsekvens> forste, HashMap<String, Subsekvens> andre) {
        laas.lock();
        try{
            return Register.samleMap(forste, andre);
        }finally{
            laas.unlock();
        } 
    }

    public ArrayList <String> FaaFilnavn(String[] args){
        laas.lock();
        try{
            return Register.FaaFilnavn(args);
        }finally{
            laas.unlock();
        } 
    }

    public void hoyesteAntall(HashMap<String, Subsekvens> mapet){
        laas.lock();
        try{
            Register.hoyesteAntall(mapet);
        }finally{
            laas.unlock();
        } 
    }

    public static HashMap<String, Subsekvens> lesFraFil(String filnavn){
        laas.lock();
        try{
            return Register.lesFraFil(filnavn);
        }finally{
            laas.unlock();
        } 
    }
    public ArrayList<HashMap<String, Subsekvens>> HentRegister(){
        laas.lock();
        try{
            return Register.HentRegister();
        }finally{
            laas.unlock();
        } 
    }


    public ArrayList<HashMap<String, Subsekvens>> HentUtTo(){
        laas.lock();
        try{
            return Register.hentToHashmap();
        }finally{
            laas.unlock();
        }   
    }



}
