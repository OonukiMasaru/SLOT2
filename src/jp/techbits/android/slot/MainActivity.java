package jp.techbits.android.slot;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{

	String slotName[] = {
			"北斗の拳 転生の章",
			"創聖のアクエリオンⅡ",
			"Android developer",
			"Youtube",
			"ニコニコ動画",
			"鬼浜爆走紅連隊 友情挽歌編",
			"新鬼武者 再臨",
			"鬼の城",
			"キャッツ・アイ-コレクション奪還作戦",
			"キャプテンパルサー",
			"スナイパイ72",
			"バイオハザード5",
			"アントニオ猪木が伝説にするパチスロ機",
			"花の慶次～天に愛されし男～",
			"バジリスク～甲賀忍法帳～Ⅱ"
	};

	
	// 1段目に表示するテキスト
	String[] information = { 
		"report1", "report2", "report3", "report4", "report5", 
        "report6", "report7", "report8", "report9", "report10",
        "report11", "report12",
    }; 
    // 2段目に表示するテキスト
	String[] title = new String[] {
		"Air quality forecast", "Typhoon course information",
	    "Weather map", "Weather forecast","Wind direction",
	    "UV forecast", "Tropical low pressure system","Fog alert",
	    "Celsius degrees", "Humidity", "Snow pellets", "Thunder",
    }; 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

//		// リストビューに表示するためのデータを設定
//        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
//              this, android.R.layout.simple_list_item_1);
//
//        for(int index = 0; index < slotName.length; index++) {
//        	adapter.add(slotName[index]);
//        }

//        // データを格納するためのArrayListを宣言
//        ArrayList<HashMap<String, String>> data
//			= new ArrayList<HashMap<String, String>>();
//			
//		// 12項目分の繰り返し処理
//        for(int i = 0; i< 12; i++){
//        	// HashMap型のインスタンスを生成
//        	HashMap<String, String> temp
//				= new HashMap<String, String>();
//				
//			// 配列informationの各要素をtempに代入
//        	temp.put("key_information", information[i]);
//        	
//			// 配列titleの各要素をtempに代入
//        	temp.put("key_title", title[i]);
//        	
//        	// 作成したtempをdataに追加
//        	data.add(temp);
//         }
//		// dataにレイアウトrow.xmlを関連付けたアダプターを生成
//        SimpleAdapter adapter
//			= new SimpleAdapter(this, data, R.layout.item, 
//				new String[]{"key_information", "key_title"},
//				new int[]{R.id.information, R.id.title}
//        );

        // リソースに準備した画像ファイルからBitmapを作成しておく
        Bitmap image;
        image = BitmapFactory.decodeResource(getResources(), R.drawable.star_on);
 
        // データの作成
        List<CustomData> objects = new ArrayList<CustomData>();

        
        for(int index = 0; index < slotName.length; index++) {
        	
        	CustomData item = new CustomData();
        	item.setTextData(slotName[index]);
        	item.setImagaData(image);
            objects.add(item);
 
//        CustomData item2 = new CustomData();
//        item2.setImagaData(image);
//        item2.setTextData("The second");
// 
//        CustomData item3 = new CustomData();
//        item3.setImagaData(image);
//        item3.setTextData("Il terzo");
// 
//        objects.add(item1);
//        objects.add(item2);
//        objects.add(item3);
        }
 
        CustomAdapter customAdapater = new CustomAdapter(this, 0, objects);
 
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(customAdapater);
        listView.setOnItemClickListener(this);

//        listView.setOnItemClickListener(this);
		
//        // リストビューにデータを設定
//        ListView listView1 = (ListView)findViewById(R.id.listView1);
//        listView1.setAdapter(adapter);
//		// リスナーを登録する
//        listView1.setOnItemClickListener(this);
	}

	public void onItemClick(
			AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, slotName[position], Toast.LENGTH_SHORT).show();

		switch(position) {
			case 0:
				sendMessage("file:///android_asset/hokuto_tensei/hokuto_tensei.htm");
				break;
			case 1:
				sendMessage("http://www.sankyo-fever.co.jp/special/saquarion2/");
				break;
			case 2:
				sendMessage("http://developer.android.com/index.html");
				break;
			case 3:
				sendMessage("http://www.youtube.com/");
				break;
			case 4:
				sendMessage("http://www.nicovideo.jp/");
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendMessage(String url) {
		Intent newIntent = new Intent(this, Browser.class);
		newIntent.putExtra("String Value", url);
//		newIntent.putExtra("String Value", "http://www.sammy.co.jp/japanese/product/pachislot/hokutotensei/sp");
		try {
			startActivity(newIntent);
		} catch(Exception e) {
			Toast.makeText(this,
					"Browserへの画面遷移に失敗しました。", Toast.LENGTH_LONG)
					.show();
		}
	}
	
}
