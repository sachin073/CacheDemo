package Mgr;

public class CacheMgr {


    private CacheMgr cacheMgr=null;
    private CacheMgr(){
        return;
    }


    public CacheMgr getCacheMgr() {
        if (cacheMgr ==null){
            cacheMgr = new CacheMgr();
        }

        return cacheMgr;
    }





}
