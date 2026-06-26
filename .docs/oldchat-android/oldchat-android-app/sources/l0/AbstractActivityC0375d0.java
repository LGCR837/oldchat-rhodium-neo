package l0;

import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import java.util.ArrayList;
import java.util.List;
import o.c;

/* JADX INFO: renamed from: l0.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0375d0 extends AbstractActivityC0377e0 {

    /* JADX INFO: renamed from: l0.d0$a */
    public class a implements a.h {
        public a() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            AbstractActivityC0375d0.this.j0(false);
            if (str == null || str.length() <= 0) {
                return;
            }
            AbstractActivityC0375d0.this.g0("推荐请求失败: " + str);
            Toast.makeText(AbstractActivityC0375d0.this, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(List list) {
            AbstractActivityC0375d0.this.j0(false);
            AbstractActivityC0375d0 abstractActivityC0375d0 = AbstractActivityC0375d0.this;
            StringBuilder sb = new StringBuilder();
            sb.append("loadRecommend: success size=");
            sb.append(list != null ? list.size() : 0);
            abstractActivityC0375d0.h0(sb.toString());
            AbstractActivityC0375d0 abstractActivityC0375d02 = AbstractActivityC0375d0.this;
            com.im.oldchat.ui.Z z2 = abstractActivityC0375d02.f6037G;
            if (z2 != null) {
                z2.g(abstractActivityC0375d02.p0(list));
            }
            AbstractActivityC0375d0 abstractActivityC0375d03 = AbstractActivityC0375d0.this;
            abstractActivityC0375d03.f6044N = false;
            abstractActivityC0375d03.f6045O = "";
            abstractActivityC0375d03.f6046P = 0;
            abstractActivityC0375d03.f6048R = true;
            TextView textView = abstractActivityC0375d03.f6036F;
            if (textView != null) {
                textView.setText(abstractActivityC0375d03.f6042L ? f0.j.O1 : f0.j.N1);
            }
            AbstractActivityC0375d0 abstractActivityC0375d04 = AbstractActivityC0375d0.this;
            TextView textView2 = abstractActivityC0375d04.f6052x;
            if (textView2 != null) {
                textView2.setText(abstractActivityC0375d04.f6042L ? f0.j.g2 : f0.j.h2);
            }
        }
    }

    /* JADX INFO: renamed from: l0.d0$b */
    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f6020a;

        public b(EditText editText) {
            this.f6020a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractActivityC0375d0.this.r0(this.f6020a);
        }
    }

    /* JADX INFO: renamed from: l0.d0$c */
    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f6022a;

        public c(EditText editText) {
            this.f6022a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            AbstractActivityC0375d0.this.r0(this.f6022a);
            AbstractActivityC0375d0.this.O0();
        }
    }

    /* JADX INFO: renamed from: l0.d0$d */
    public class d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f6024a;

        public d(EditText editText) {
            this.f6024a = editText;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String strTrim = this.f6024a.getText() != null ? this.f6024a.getText().toString().trim() : "";
            if (strTrim.length() == 0) {
                Toast.makeText(AbstractActivityC0375d0.this, f0.j.c2, 0).show();
            } else {
                AbstractActivityC0375d0.this.r0(this.f6024a);
                AbstractActivityC0375d0.this.T0(strTrim);
            }
        }
    }

    /* JADX INFO: renamed from: l0.d0$e */
    public class e implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f6026a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6027b;

        public e(boolean z2, int i2) {
            this.f6026a = z2;
            this.f6027b = i2;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            AbstractActivityC0375d0.this.j0(false);
            AbstractActivityC0375d0.this.f6047Q = false;
            if (str == null || str.length() <= 0) {
                return;
            }
            AbstractActivityC0375d0.this.g0("搜索失败: " + str);
            Toast.makeText(AbstractActivityC0375d0.this, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.SearchResult searchResult) {
            AbstractActivityC0375d0.this.j0(false);
            AbstractActivityC0375d0 abstractActivityC0375d0 = AbstractActivityC0375d0.this;
            abstractActivityC0375d0.f6047Q = false;
            List listR0 = abstractActivityC0375d0.R0(searchResult);
            com.im.oldchat.ui.Z z2 = AbstractActivityC0375d0.this.f6037G;
            if (z2 != null) {
                if (this.f6026a) {
                    z2.g(listR0);
                } else if (listR0 != null && !listR0.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    int count = AbstractActivityC0375d0.this.f6037G.getCount();
                    for (int i2 = 0; i2 < count; i2++) {
                        Object item = AbstractActivityC0375d0.this.f6037G.getItem(i2);
                        if (item instanceof BiliModels.RecommendItem) {
                            arrayList.add((BiliModels.RecommendItem) item);
                        }
                    }
                    arrayList.addAll(listR0);
                    AbstractActivityC0375d0.this.f6037G.g(arrayList);
                }
            }
            AbstractActivityC0375d0 abstractActivityC0375d02 = AbstractActivityC0375d0.this;
            abstractActivityC0375d02.f6046P = this.f6027b;
            abstractActivityC0375d02.f6048R = abstractActivityC0375d02.P0(searchResult, listR0);
        }
    }

    @Override // l0.AbstractActivityC0377e0
    public void F0(String str) {
        j0(true);
        h0("loadRecommend: start");
        com.im.oldchat.bili.a.D(str, h0.a.c(this), new a());
    }

    public void O0() {
        this.f6044N = false;
        this.f6045O = "";
        this.f6046P = 0;
        this.f6048R = true;
        String strB = h0.a.b(this);
        boolean zE = h0.a.e(this);
        if (strB == null || strB.length() <= 0 || zE) {
            H0();
        } else {
            F0(strB);
        }
    }

    public boolean P0(BiliModels.SearchResult searchResult, List list) {
        BiliModels.SearchData searchData;
        if (searchResult == null || (searchData = searchResult.data) == null) {
            return list != null && list.size() >= 20;
        }
        int i2 = searchData.numPages;
        return i2 <= 0 ? list != null && list.size() >= 20 : searchData.page < i2;
    }

    public void Q0(int i2, boolean z2) {
        String str;
        if (this.f6047Q || (str = this.f6045O) == null || str.length() == 0) {
            return;
        }
        this.f6047Q = true;
        j0(true);
        h0("loadSearch: keyword=" + this.f6045O + " page=" + i2);
        com.im.oldchat.bili.a.E(this.f6045O, i2, h0.a.c(this), new e(z2, i2));
    }

    public List R0(BiliModels.SearchResult searchResult) {
        BiliModels.SearchData searchData;
        ArrayList arrayList = new ArrayList();
        if (searchResult != null && searchResult.code == 0 && (searchData = searchResult.data) != null && searchData.result != null) {
            for (int i2 = 0; i2 < searchResult.data.result.size(); i2++) {
                BiliModels.SearchItem searchItem = searchResult.data.result.get(i2);
                if (searchItem != null) {
                    BiliModels.RecommendItem recommendItem = new BiliModels.RecommendItem();
                    recommendItem.cardType = "search";
                    recommendItem.cardGoto = "av";
                    recommendItem.gotoType = "av";
                    long j2 = searchItem.aid;
                    if (j2 > 0) {
                        recommendItem.param = String.valueOf(j2);
                    } else {
                        String str = searchItem.bvid;
                        if (str != null && str.length() > 0) {
                            recommendItem.param = searchItem.bvid;
                        }
                    }
                    recommendItem.cover = com.im.oldchat.bili.c.i(searchItem.pic);
                    recommendItem.title = B0(searchItem.title);
                    recommendItem.duration = searchItem.duration;
                    String str2 = searchItem.play;
                    if (str2 != null) {
                        recommendItem.playCount = String.valueOf(str2);
                    }
                    int i3 = searchItem.danmaku;
                    if (i3 > 0) {
                        recommendItem.danmakuCount = String.valueOf(i3);
                    }
                    BiliModels.RecommendArgs recommendArgs = new BiliModels.RecommendArgs();
                    recommendItem.args = recommendArgs;
                    recommendArgs.upName = searchItem.author;
                    long j3 = searchItem.aid;
                    if (j3 > 0) {
                        recommendArgs.aid = j3;
                    }
                    arrayList.add(recommendItem);
                }
            }
        }
        return arrayList;
    }

    public void S0() {
        EditText editText = new EditText(this);
        editText.setHint(getString(f0.j.b2));
        editText.setSingleLine(true);
        editText.setImeOptions(3);
        String str = this.f6045O;
        if (str == null) {
            str = "";
        }
        editText.setText(str);
        int i2 = (int) (getResources().getDisplayMetrics().density * 16.0f);
        editText.setPadding(i2, i2, i2, i2);
        new c.a(this).s(f0.j.d2).u(editText).p(f0.j.L1, new d(editText)).l(f0.j.J1, new c(editText)).j(f0.j.I1, new b(editText)).v();
    }

    public void T0(String str) {
        this.f6044N = true;
        this.f6045O = str;
        this.f6046P = 0;
        this.f6048R = true;
        com.im.oldchat.ui.Z z2 = this.f6037G;
        if (z2 != null) {
            z2.g(null);
        }
        TextView textView = this.f6036F;
        if (textView != null) {
            textView.setText(f0.j.a2);
        }
        Q0(1, true);
    }
}
