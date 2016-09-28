package com.grimar.feedy;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;

import com.grimar.feedy.adapters.StringListItem;
import com.grimar.feedy.data.Business;
import com.grimar.feedy.data.FastLinks;
import com.grimar.feedy.data.Fun;
import com.grimar.feedy.data.Lifestyle;
import com.grimar.feedy.data.News;
import com.grimar.feedy.data.Sport;
import com.grimar.feedy.data.Tech;
import com.roudj.feedy.R;

public class ClickHandler {

    private MainActivity mainActivity = null;

    public ClickHandler(Context context) {
        mainActivity = (MainActivity) context;
    }

    public void processClick(View v) {

        switch (v.getId()) {
            case R.id.swipe_stripe:
                mainActivity.toggleArticleList();
                break;
            case R.id.hidden_list_frame:
                mainActivity.toggleArticleList();
                break;
            case R.id.hide_btn:
                mainActivity.toggleArticleList();
                break;
            case R.id.btn1:
                mainActivity.resetPortaliClicked();
                if (!mainActivity.getMenuPortaliListExpanded()) {
                    mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_1_CAPS));
                    mainActivity.getBtn1().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                    mainActivity.getPortalsArray().clear();
                    for (int iterx = 0; iterx < News.getPortalName().size(); iterx++) {
                        mainActivity.getPortalsArray().add((new StringListItem(News.getPortalName().get(iterx), News.getPortalURL().get(iterx), News.getPortalEncoding().get(iterx))));
                    }
                    mainActivity.getPortalsAdapter().notifyDataSetChanged();
                    mainActivity.togglePortaliList();
                    mainActivity.updatePortaliListeners();
                    mainActivity.setLastMenuChoice(1);
                } else if (mainActivity.getMenuPortaliListExpanded()) {
                    if (mainActivity.getLastMenuChoice() == 1) {
                        mainActivity.togglePortaliList();
                    } else {
                        mainActivity.getBtn1().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                        mainActivity.togglePortaliList();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.getPortalsArray().clear();
                                mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_1_CAPS));
                                for (int iterx = 0; iterx < News.getPortalName().size(); iterx++) {
                                    mainActivity.getPortalsArray().add((new StringListItem(News.getPortalName().get(iterx), News.getPortalURL().get(iterx), News.getPortalEncoding().get(iterx))));
                                }
                                mainActivity.getPortalsAdapter().notifyDataSetChanged();
                                mainActivity.togglePortaliList();
                                mainActivity.updatePortaliListeners();
                                mainActivity.setLastMenuChoice(1);
                            }
                        }, 200);
                    }
                }
                break;
            case R.id.btn2:
                mainActivity.resetPortaliClicked();
                if (!mainActivity.getMenuPortaliListExpanded()) {
                    mainActivity.getBtn2().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                    mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_2_CAPS));
                    mainActivity.getPortalsArray().clear();
                    for (int iterx = 0; iterx < Sport.getPortalName().size(); iterx++) {
                        mainActivity.getPortalsArray().add((new StringListItem(Sport.getPortalName().get(iterx), Sport.getPortalURL().get(iterx), Sport.getPortalEncoding().get(iterx))));
                    }
                    mainActivity.getPortalsAdapter().notifyDataSetChanged();
                    mainActivity.togglePortaliList();
                    mainActivity.updatePortaliListeners();
                    mainActivity.setLastMenuChoice(2);
                } else if (mainActivity.getMenuPortaliListExpanded()) {
                    if (mainActivity.getLastMenuChoice() == 2) {
                        mainActivity.togglePortaliList();
                    } else {
                        mainActivity.getBtn2().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                        mainActivity.togglePortaliList();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_2_CAPS));
                                mainActivity.getPortalsArray().clear();
                                for (int iterx = 0; iterx < Sport.getPortalName().size(); iterx++) {
                                    mainActivity.getPortalsArray().add((new StringListItem(Sport.getPortalName().get(iterx), Sport.getPortalURL().get(iterx), Sport.getPortalEncoding().get(iterx))));
                                }
                                mainActivity.getPortalsAdapter().notifyDataSetChanged();
                                mainActivity.togglePortaliList();
                                mainActivity.updatePortaliListeners();
                                mainActivity.setLastMenuChoice(2);
                            }
                        }, 200);
                    }
                }
                break;
            case R.id.btn3:
                mainActivity.resetPortaliClicked();
                if (!mainActivity.getMenuPortaliListExpanded()) {
                    mainActivity.getBtn3().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                    mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_3_CAPS));
                    mainActivity.getPortalsArray().clear();
                    for (int iterx = 0; iterx < Lifestyle.getPortalName().size(); iterx++) {
                        mainActivity.getPortalsArray().add((new StringListItem(Lifestyle.getPortalName().get(iterx), Lifestyle.getPortalURL().get(iterx), Lifestyle.getPortalEncoding().get(iterx))));
                    }
                    mainActivity.getPortalsAdapter().notifyDataSetChanged();
                    mainActivity.togglePortaliList();
                    mainActivity.updatePortaliListeners();
                    mainActivity.setLastMenuChoice(3);
                } else if (mainActivity.getMenuPortaliListExpanded()) {
                    if (mainActivity.getLastMenuChoice() == 3) {
                        mainActivity.togglePortaliList();
                    } else {
                        mainActivity.getBtn3().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                        mainActivity.togglePortaliList();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_3_CAPS));
                                mainActivity.getPortalsArray().clear();
                                for (int iterx = 0; iterx < Lifestyle.getPortalName().size(); iterx++) {
                                    mainActivity.getPortalsArray().add((new StringListItem(Lifestyle.getPortalName().get(iterx), Lifestyle.getPortalURL().get(iterx), Lifestyle.getPortalEncoding().get(iterx))));
                                }
                                mainActivity.getPortalsAdapter().notifyDataSetChanged();
                                mainActivity.togglePortaliList();
                                mainActivity.updatePortaliListeners();
                                mainActivity.setLastMenuChoice(3);
                            }
                        }, 200);
                    }
                }
                break;
            case R.id.btn4:
                mainActivity.resetPortaliClicked();
                if (!mainActivity.getMenuPortaliListExpanded()) {
                    mainActivity.getBtn4().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                    mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_4_CAPS));
                    mainActivity.getPortalsArray().clear();
                    for (int iterx = 0; iterx < Business.getPortalName().size(); iterx++) {
                        mainActivity.getPortalsArray().add((new StringListItem(Business.getPortalName().get(iterx), Business.getPortalURL().get(iterx), Business.getPortalEncoding().get(iterx))));
                    }
                    mainActivity.getPortalsAdapter().notifyDataSetChanged();
                    mainActivity.togglePortaliList();
                    mainActivity.updatePortaliListeners();
                    mainActivity.setLastMenuChoice(4);
                } else if (mainActivity.getMenuPortaliListExpanded()) {
                    if (mainActivity.getLastMenuChoice() == 4) {
                        mainActivity.togglePortaliList();
                    } else {
                        mainActivity.getBtn4().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                        mainActivity.togglePortaliList();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_4_CAPS));
                                mainActivity.getPortalsArray().clear();
                                for (int iterx = 0; iterx < Business.getPortalName().size(); iterx++) {
                                    mainActivity.getPortalsArray().add((new StringListItem(Business.getPortalName().get(iterx), Business.getPortalURL().get(iterx), Business.getPortalEncoding().get(iterx))));
                                }
                                mainActivity.getPortalsAdapter().notifyDataSetChanged();
                                mainActivity.togglePortaliList();
                                mainActivity.updatePortaliListeners();
                                mainActivity.setLastMenuChoice(4);
                            }
                        }, 200);
                    }
                }
                break;
            case R.id.btn5:
                mainActivity.resetPortaliClicked();
                if (!mainActivity.getMenuPortaliListExpanded()) {
                    mainActivity.getBtn5().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                    mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_5_CAPS));
                    mainActivity.getPortalsArray().clear();
                    for (int iterx = 0; iterx < Fun.getPortalName().size(); iterx++) {
                        mainActivity.getPortalsArray().add((new StringListItem(Fun.getPortalName().get(iterx), Fun.getPortalURL().get(iterx), Fun.getPortalEncoding().get(iterx))));
                    }
                    mainActivity.getPortalsAdapter().notifyDataSetChanged();
                    mainActivity.togglePortaliList();
                    mainActivity.updatePortaliListeners();
                    mainActivity.setLastMenuChoice(5);
                } else if (mainActivity.getMenuPortaliListExpanded()) {
                    if (mainActivity.getLastMenuChoice() == 5) {
                        mainActivity.togglePortaliList();
                    } else {
                        mainActivity.getBtn5().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                        mainActivity.togglePortaliList();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_5_CAPS));
                                mainActivity.getPortalsArray().clear();
                                for (int iterx = 0; iterx < Fun.getPortalName().size(); iterx++) {
                                    mainActivity.getPortalsArray().add((new StringListItem(Fun.getPortalName().get(iterx), Fun.getPortalURL().get(iterx), Fun.getPortalEncoding().get(iterx))));
                                }
                                mainActivity.getPortalsAdapter().notifyDataSetChanged();
                                mainActivity.togglePortaliList();
                                mainActivity.updatePortaliListeners();
                                mainActivity.setLastMenuChoice(5);
                            }
                        }, 200);
                    }
                }
                break;
            case R.id.btn6:
                mainActivity.resetPortaliClicked();
                if (!mainActivity.getMenuPortaliListExpanded()) {
                    mainActivity.getBtn6().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                    mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_6_CAPS));
                    mainActivity.getPortalsArray().clear();
                    for (int iterx = 0; iterx < Tech.getPortalName().size(); iterx++) {
                        mainActivity.getPortalsArray().add((new StringListItem(Tech.getPortalName().get(iterx), Tech.getPortalURL().get(iterx), Tech.getPortalEncoding().get(iterx))));
                    }
                    mainActivity.getPortalsAdapter().notifyDataSetChanged();
                    mainActivity.togglePortaliList();
                    mainActivity.updatePortaliListeners();
                    mainActivity.setLastMenuChoice(6);
                } else if (mainActivity.getMenuPortaliListExpanded()) {
                    if (mainActivity.getLastMenuChoice() == 6) {
                        mainActivity.togglePortaliList();
                    } else {
                        mainActivity.getBtn6().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                        mainActivity.togglePortaliList();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.getTextKategorija().setText(mainActivity.getResources().getString(R.string.string_btn_6_CAPS));
                                mainActivity.getPortalsArray().clear();
                                for (int i = 0; i < Tech.getPortalName().size(); i++) {
                                    mainActivity.getPortalsArray().add((new StringListItem(Tech.getPortalName().get(i), Tech.getPortalURL().get(i), Tech.getPortalEncoding().get(i))));
                                }
                                mainActivity.getPortalsAdapter().notifyDataSetChanged();
                                mainActivity.togglePortaliList();
                                mainActivity.updatePortaliListeners();
                                mainActivity.setLastMenuChoice(6);
                            }
                        }, 200);
                    }
                }
                break;
            case R.id.btn7:
                mainActivity.resetPortaliClicked();
                mainActivity.getBtn7().setBackgroundResource(R.drawable.cat_item_mid_clicked);
                mainActivity.getQuickLinksArray().clear();
                mainActivity.getTextPortal().setText(mainActivity.getResources().getString(R.string.string_btn_7));
                for (int iterx = 0; iterx < FastLinks.getPortalName().size(); iterx++) {
                    mainActivity.getQuickLinksArray().add((new StringListItem(FastLinks.getPortalName().get(iterx), FastLinks.getPortalURL().get(iterx))));
                }

                mainActivity.setmArticlesListView((ListView) mainActivity.findViewById(R.id.articles_list_view));
                mainActivity.getmArticlesListView().setAdapter(mainActivity.getQuickLinksAdapter());

                WebView tempWebView = (WebView) mainActivity.findViewById(R.id.web_view);
                tempWebView.loadUrl(FastLinks.getPortalURL().get(0));
                if (!mainActivity.getMenuListExpanded()) {
                    mainActivity.toggleArticleList();
                }
                if (mainActivity.getMenuPortaliListExpanded()) {
                    mainActivity.togglePortaliList();
                }
                break;
        }
    }
}
