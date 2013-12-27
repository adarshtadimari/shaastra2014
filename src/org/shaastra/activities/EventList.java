package org.shaastra.activities;

import org.shaastra.helper.MyCard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;



import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;

public class EventList extends Activity {
	Intent i;
	 String db[]={"Fire N Ice","Lunar Rover Challenge","Ultimate Engineer","Contraptions","Robowars","Junkyard Wars","Robotics"};
	 String af[]={"Aerobotics","Wright Design","Paper Planes","TopGun","AirShow","Boeing National Aeromodelling Competition"};
	 String cod1[]={"Open Programmin Contest","Reverse Coding","Triathlon","Debugging","Code Obfuscation","Automania","Hackfest Workshop"};
	 String inv[]={"ProjectX","Shaastra Cube Open","Math Modelling","Puzzle Champ"};
	 String qui[]={"Shaastra Junior Quiz","Shaastra Main Quiz","How Things Work","Auto Quiz"};
	 String on[]={"Online Puzzle Champ","Online Math Modelling","Finance and Consultancy"};
	 String df[]={"Robo Oceana","Forensics","Shaastra Circuit Design Challenge","Chemical X","Master Builder","Desmod","Onspot Desmod"};
	 String sp[]={"Sustainable CityScape","Paper and Poster Presentation","Shaastra Junior","IIT Madras Symposium","Ideas Challenge"};
	 String ws[]={"Autonomous Robotics","Chuckglider","Hovercraft","Quadrotor","3D Animation","Forensics","Streax","Rubiks Cube","Android","Manual Robotics","Paper Planes"};
	 String ex[]={"Product Launch","Tech Lounge","Magic Materials"};
	 String be[]={"Case Study","Vittaneeti","Estimus"};
	 String ase[]={"Erricson IDP","Eaton IDP"};
	 String try1[][]={db,af,cod1,inv,qui,on,df,sp,ws,ex,be,ase};
	 
	 
	 
	private CardUI mCardView;
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//To remove title
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	i=new Intent(this,EventActivity.class);
        //to remove notification bar
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_event_list);

		// init CardView
		mCardView = (CardUI) findViewById(R.id.cardsview);
		mCardView.setSwipeable(false);

		MyCard designAndBuild = new MyCard("Design and Build","Want to destroy other bots with your bot? Race ? or are you interested in making machines using junk,this is the place for you. ");
		designAndBuild.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(db,0);

			}
		});
		MyCard aerofest = new MyCard("Aerofest","For pilots who want to put their skills to test, engineers who want to build anything which flies and those who enjoy aerial displays,this is where you should be.");
		aerofest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(af,1);

			}
		});
		MyCard coding = new MyCard("Coding Events","Does the phrase 'Binary Search' pop-up in your head when I say 'Search for an element in a sorted array'? Then you are in the right tab. ");
		coding.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(cod1,2);

			}
		});
		MyCard involve = new MyCard("Involve","Get involved in solving puzzles and creating tools from everyday materials.");
		involve.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(inv,3);

			}
		});
		MyCard quizzes = new MyCard("Quizzes","This is the place to be for hardcore quizzers and those who want to have some high IQ fun");
		quizzes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(qui,4);

			}
		});
		MyCard onlineEvents = new MyCard("Online Events","Can't be here for Shaastra? Have access to internet ? Then these events are for you.");
		onlineEvents.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(on,5);

			}
		});
		MyCard depflag = new MyCard("Department Flagships","Crime Scene Investigation, Circuit Designing ,Floating Robots. This has it all. ");
		depflag.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(df,6);

			}
		});
		MyCard spotlight = new MyCard("Spotlight","Have a great idea? These events help you showcase your ideas and opinions");
		spotlight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(sp,7);

			}
		});
		MyCard workshops = new MyCard("Workshops","Includes Aerobotics,Animation,Forensics,Robotics,Android and Rubiks Cube workshop");
		workshops.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(ws,8);

			}
		});
		MyCard exib = new MyCard("Exhibitions And Shows","Shaastra celebrates science and technology with simple experiments,spectacular demonstrations,product launches and shows.");
		exib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(ex,9);

			}
		});
		MyCard business = new MyCard("Business Events","For the budding managers,executives who think big");
		business.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(be,10);

			}
		});
		MyCard associatedEvents = new MyCard("Associated Events","Includes Industry Defined Problems");
		associatedEvents.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inflateList(ase,11);

			}
		});












		// add AndroidViews Cards
		mCardView.addCard(aerofest);
		mCardView.addCardToLastStack(coding);
		mCardView.addCardToLastStack(designAndBuild);
		mCardView.addCard(involve);
		mCardView.addCardToLastStack(quizzes);
		mCardView.addCardToLastStack(onlineEvents);
		mCardView.addCardToLastStack(depflag);
		mCardView.addCard(spotlight);
		mCardView.addCardToLastStack(workshops);
		mCardView.addCardToLastStack(exib);
		mCardView.addCardToLastStack(business);
		
		
		
		
		// draw cards
		mCardView.refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.event_list, menu);
		return true;
	}

public void inflateList(String[] s,final int eventCatIndex)
{
	AlertDialog.Builder builderSingle = new AlertDialog.Builder(
            EventList.this);
	
    //builderSingle.setIcon(R.drawable.ic_launcher);
   // builderSingle.setTitle("Events");
    
  //  String s[]={"Fire N Ice","Lunar Rover Challenge","Ultimate Engineer","Contraptions","Robowars","Junkyard Wars","Robotics"};
   
    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
            EventList.this,
            R.layout.custom_list_item,s);
    

    builderSingle.setAdapter(arrayAdapter,
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                   
                   
                   Log.d("Which",String.valueOf(which));
                   i.putExtra("categoryindex", eventCatIndex);
                   i.putExtra("eventIndex", which);
                   Log.d("Event",try1[eventCatIndex][which]);
                   startActivity(i);
                }
            });
    AlertDialog al=builderSingle.create();
    al.setCanceledOnTouchOutside(true);
    al.show();
    
        //builderSingle.show();
        
}



}