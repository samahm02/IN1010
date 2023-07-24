public class Lege implements Comparable<Lege>{

    String navn;
    IndeksertListe<Resept> utskrevedeResepter;

    

    public Lege(String navn){
        this.navn = navn;
        utskrevedeResepter = new IndeksertListe<>();
    }

    public String hentLegeNavn(){
        return navn;

    }



    public IndeksertListe<Resept> hentResepter() {
        return utskrevedeResepter;
    }
    private void gyldigLegemiddel(Lege lege, Legemiddel middel) throws UlovligUtskrift{
      if ((middel instanceof Narkotisk) && !(lege instanceof Spesialist)){
        throw new UlovligUtskrift(lege, middel);
      }
    }
    
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift {
      gyldigLegemiddel(this, legemiddel);
        //oppretter hvitResept
        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        //legger til i listen
        utskrevedeResepter.leggTil((Resept)hvitResept);
        //legger til hos pasienten
        pasient.nyResept(hvitResept);
        //returnerer
        return hvitResept;
      }
  
    public MilResept skrivMilitaerresept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
       gyldigLegemiddel(this, legemiddel);
        //oppretter militaerresept
        MilResept militaerresept = new MilResept(legemiddel, this, pasient);
        //legger til i listen
        utskrevedeResepter.leggTil((Resept)militaerresept);
        //legger til hos pasienten
        pasient.nyResept(militaerresept);
        //returnerer
        return militaerresept;
      }
  
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
      gyldigLegemiddel(this, legemiddel);
        //oppretter pResept
        PResept pResept = new PResept(legemiddel, this, pasient, reit);
        //legger til i listen
        utskrevedeResepter.leggTil((Resept)pResept);
        //legger til hos pasienten
        pasient.nyResept(pResept);
        //returnerer
        return pResept;
      }
  
      public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        gyldigLegemiddel(this, legemiddel);
        //oppretter blaaResept
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        //legger til i listen
        utskrevedeResepter.leggTil((Resept)blaaResept);
        //legger til hos pasienten
        pasient.nyResept(blaaResept);
        //returnerer
        return blaaResept;
      }

    
    @Override
    public String toString() {
        return navn + ",0";

    }



    @Override
    public int compareTo(Lege annen) {
        return this.navn.compareTo(annen.navn);
    }
}
