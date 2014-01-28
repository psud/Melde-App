package com.patsud.melden.deprecated;

import com.patsud.melden.R;
import com.patsud.melden.R.drawable;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class HaSchreibenSimple extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		// ////////////Arrays
		String[] faecher = new String[] { "Englisch", "Deutsch", "Mathe",
				"Geschichte", "PoWi", "Religion", "Physik", "Chemie", "Bio",
				"Programmieren", "Sport" };
		String[] woher = new String[] { "Blatt", "Buch", "Aufgabe" };
		String[] seite = new String[] { "Seite: ", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "<--" };
		String[] nummer = new String[] { "Nummer: ", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "<--", "komplett", " + " };
		String[] wasMachen = new String[] { "lesen", "machen", "highlighten",
				"analysieren", "verstehen" };
		String[] notiz = new String[] { "Notiz", "abgeben", "freiwillig" };
		String[] datum = new String[] { "morgen", "2 Tagen", "3 Tagen",
				"4 Tagen", "5 Tagen", "6 Tagen", "anderes Datum" };

		String[][] multi = new String[][] {
				{ "Englisch", "Deutsch", "Mathe", "Geschichte", "PoWi",
						"Religion", "Physik", "Chemie", "Bio", "Programmieren",
						"Sport" },
				{ "Blatt", "Buch", "Aufgabe" },
				{ "Seite: ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "<--" },
				{ "Nummer: ", "1", "2", "3", "4", "5", "6", "7", "8", "9",
						"<--", "komplett", " + " },
				{ "lesen", "machen", "highlighten", "analysieren", "verstehen" },
				{ "Notiz", "abgeben", "freiwillig" },
				{ "morgen", "2 Tagen", "3 Tagen", "4 Tagen", "5 Tagen",
						"6 Tagen", "anderes Datum" }, };

		// ///////////////////////
		TextView label = new TextView(this);
		label.setText("lalalalME");
		label.setTextSize(20);
		label.setGravity(Gravity.CENTER_HORIZONTAL);
		ImageView pic = new ImageView(this);
	//	pic.setImageResource(R.drawable.greennormal);
		pic.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		pic.setAdjustViewBounds(true);
		pic.setScaleType(ScaleType.FIT_XY);
		pic.setMaxHeight(250);
		pic.setMaxWidth(250);
		// Main Layout
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		ll.setWeightSum(7);

		// ll.setGravity(Gravity.CENTER);

		// Params
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		params.weight = 1.0f;

		// Layout Row 1
		/*
		 * for (int i = 0; i<7; i++){
		 * 
		 * 
		 * Button b = new Button(this); b.setText(Integer.toString(i));
		 * b.setTextSize(20); l1.addView(b); }
		 */
		int butNum = 0;
		for (int j = 0; j < multi.length; j++) {
			ScrollView sc = new ScrollView(this);
			sc.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT));
			sc.setFillViewport(true);
			sc.setLayoutParams(params);

			LinearLayout l11 = new LinearLayout(this);
			l11.setOrientation(LinearLayout.VERTICAL);
			l11.setLayoutParams(params);
			sc.addView(l11);
			ll.addView(sc);

			for (int i = 0; i < multi[j].length; i++) {
				Button b = new Button(this);
				b.setText(multi[j][i]);
				b.setTextSize(20);
				b.setBackgroundResource(R.drawable.buttonbox);
				if (j == 2 || j == 3)
					b.setLayoutParams(new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.FILL_PARENT, 94));
				else
					b.setLayoutParams(new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.FILL_PARENT, 141));

				l11.addView(b);
				b.setOnClickListener(this);
				butNum++;
			}

		}

		setContentView(ll);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
}
