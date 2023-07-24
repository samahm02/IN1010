class UlovligUtskrift extends Exception {
    UlovligUtskrift (Lege l, Legemiddel lm) {
      super("Legen "+l.hentLegeNavn()+" har ikke lov til aa skrive ut "+lm.hentNavn());
    }
  }
  