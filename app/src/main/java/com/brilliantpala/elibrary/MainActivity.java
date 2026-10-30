package com.brilliantpala.elibrary;
import android.app.*;import android.os.*;import android.view.*;import android.webkit.*;import android.widget.*;
public class MainActivity extends Activity {
 WebView web; boolean triggered; long start;
 final String[] names={"Gemini","ChatGPT","eLibrary"};
 final String[] urls={"https://gemini.google.com/app","https://chatgpt.com/","https://elibrary.brilliantpala.org/"};
 public void onCreate(Bundle state){super.onCreate(state);web=new WebView(this);setContentView(web);web.getSettings().setJavaScriptEnabled(true);web.getSettings().setDomStorageEnabled(true);web.setWebViewClient(new WebViewClient());web.setWebChromeClient(new WebChromeClient());
 web.setOnTouchListener((v,e)->{if(e.getActionMasked()==MotionEvent.ACTION_DOWN){start=System.currentTimeMillis();triggered=false;}if(e.getPointerCount()==3&&!triggered&&System.currentTimeMillis()-start<1000){triggered=true;menu();return true;}return false;});web.loadUrl(urls[2]);}
 void menu(){LinearLayout layout=new LinearLayout(this);layout.setOrientation(LinearLayout.HORIZONTAL);layout.setPadding(12,24,12,24);AlertDialog dialog=new AlertDialog.Builder(this).setTitle("Switch website").setView(layout).create();
 for(int i=0;i<3;i++){final int j=i;LinearLayout cell=new LinearLayout(this);cell.setOrientation(LinearLayout.VERTICAL);cell.setGravity(Gravity.CENTER);TextView symbol=new TextView(this);symbol.setText(new String[]{"✦","◎","📚"}[i]);symbol.setTextSize(32);symbol.setGravity(Gravity.CENTER);TextView name=new TextView(this);name.setText(names[i]);name.setGravity(Gravity.CENTER);cell.addView(symbol);cell.addView(name);layout.addView(cell,new LinearLayout.LayoutParams(0,120,1));cell.setOnClickListener(v->{web.loadUrl(urls[j]);dialog.dismiss();});}dialog.show();}
 @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}