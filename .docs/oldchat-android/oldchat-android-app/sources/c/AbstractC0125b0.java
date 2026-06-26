package c;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: c.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0125b0 {

    /* JADX INFO: renamed from: c.b0$a */
    public static class a extends Transition.EpicenterCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f1834a;

        public a(Rect rect) {
            this.f1834a = rect;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public Rect onGetEpicenter(Transition transition) {
            return this.f1834a;
        }
    }

    /* JADX INFO: renamed from: c.b0$b */
    public static class b implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1835a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Transition f1836b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f1837c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ f f1838d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Map f1839e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Map f1840f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1841g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Transition f1842h;

        public b(View view, Transition transition, View view2, f fVar, Map map, Map map2, ArrayList arrayList, Transition transition2) {
            this.f1835a = view;
            this.f1836b = transition;
            this.f1837c = view2;
            this.f1838d = fVar;
            this.f1839e = map;
            this.f1840f = map2;
            this.f1841g = arrayList;
            this.f1842h = transition2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            View viewA;
            this.f1835a.getViewTreeObserver().removeOnPreDrawListener(this);
            Transition transition = this.f1836b;
            if (transition != null) {
                transition.removeTarget(this.f1837c);
            }
            f fVar = this.f1838d;
            if (fVar != null && (viewA = fVar.a()) != null) {
                if (!this.f1839e.isEmpty()) {
                    AbstractC0125b0.p(this.f1840f, viewA);
                    this.f1840f.keySet().retainAll(this.f1839e.values());
                    for (Map.Entry entry : this.f1839e.entrySet()) {
                        View view = (View) this.f1840f.get((String) entry.getValue());
                        if (view != null) {
                            view.setTransitionName((String) entry.getKey());
                        }
                    }
                }
                if (this.f1836b != null) {
                    AbstractC0125b0.i(this.f1841g, viewA);
                    this.f1841g.removeAll(this.f1840f.values());
                    this.f1841g.add(this.f1837c);
                    AbstractC0125b0.d(this.f1836b, this.f1841g);
                }
            }
            AbstractC0125b0.o(this.f1842h, this.f1836b, this.f1841g, true);
            return true;
        }
    }

    /* JADX INFO: renamed from: c.b0$c */
    public static class c extends Transition.EpicenterCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f1843a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e f1844b;

        public c(e eVar) {
            this.f1844b = eVar;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public Rect onGetEpicenter(Transition transition) {
            View view;
            if (this.f1843a == null && (view = this.f1844b.f1856a) != null) {
                this.f1843a = AbstractC0125b0.q(view);
            }
            return this.f1843a;
        }
    }

    /* JADX INFO: renamed from: c.b0$d */
    public static class d implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1845a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Transition f1846b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1847c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Transition f1848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1849e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Transition f1850f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1851g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Map f1852h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1853i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ Transition f1854j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ View f1855k;

        public d(View view, Transition transition, ArrayList arrayList, Transition transition2, ArrayList arrayList2, Transition transition3, ArrayList arrayList3, Map map, ArrayList arrayList4, Transition transition4, View view2) {
            this.f1845a = view;
            this.f1846b = transition;
            this.f1847c = arrayList;
            this.f1848d = transition2;
            this.f1849e = arrayList2;
            this.f1850f = transition3;
            this.f1851g = arrayList3;
            this.f1852h = map;
            this.f1853i = arrayList4;
            this.f1854j = transition4;
            this.f1855k = view2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f1845a.getViewTreeObserver().removeOnPreDrawListener(this);
            Transition transition = this.f1846b;
            if (transition != null) {
                AbstractC0125b0.v(transition, this.f1847c);
                AbstractC0125b0.o(this.f1846b, this.f1848d, this.f1849e, false);
                AbstractC0125b0.o(this.f1846b, this.f1850f, this.f1851g, false);
            }
            Transition transition2 = this.f1848d;
            if (transition2 != null) {
                AbstractC0125b0.v(transition2, this.f1849e);
                AbstractC0125b0.o(this.f1848d, this.f1846b, this.f1847c, false);
                AbstractC0125b0.o(this.f1848d, this.f1850f, this.f1851g, false);
            }
            Transition transition3 = this.f1850f;
            if (transition3 != null) {
                AbstractC0125b0.v(transition3, this.f1851g);
            }
            for (Map.Entry entry : this.f1852h.entrySet()) {
                ((View) entry.getValue()).setTransitionName((String) entry.getKey());
            }
            int size = this.f1853i.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f1854j.excludeTarget((View) this.f1853i.get(i2), false);
            }
            this.f1854j.excludeTarget(this.f1855k, false);
            return true;
        }
    }

    /* JADX INFO: renamed from: c.b0$e */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f1856a;
    }

    /* JADX INFO: renamed from: c.b0$f */
    public interface f {
        View a();
    }

    public static void d(Object obj, ArrayList arrayList) {
        Transition transitionA = AbstractC0115G.a(obj);
        int i2 = 0;
        if (Z.a(transitionA)) {
            TransitionSet transitionSetA = AbstractC0122a0.a(transitionA);
            int transitionCount = transitionSetA.getTransitionCount();
            while (i2 < transitionCount) {
                d(transitionSetA.getTransitionAt(i2), arrayList);
                i2++;
            }
            return;
        }
        if (s(transitionA) || !t(transitionA.getTargets())) {
            return;
        }
        int size = arrayList.size();
        while (i2 < size) {
            transitionA.addTarget((View) arrayList.get(i2));
            i2++;
        }
    }

    public static void e(Object obj, Object obj2, Object obj3, View view, f fVar, View view2, e eVar, Map map, ArrayList arrayList, ArrayList arrayList2, Map map2, Map map3, ArrayList arrayList3) {
        Transition transitionA = AbstractC0115G.a(obj);
        Transition transitionA2 = AbstractC0115G.a(obj3);
        Transition transitionA3 = AbstractC0115G.a(obj2);
        o(transitionA, transitionA2, arrayList2, true);
        if (obj == null && obj2 == null) {
            return;
        }
        if (transitionA != null) {
            transitionA.addTarget(view2);
        }
        if (obj2 != null) {
            y(transitionA3, view2, map2, arrayList3);
            o(transitionA, transitionA3, arrayList3, true);
            o(transitionA2, transitionA3, arrayList3, true);
        }
        view.getViewTreeObserver().addOnPreDrawListener(new b(view, transitionA, view2, fVar, map, map3, arrayList, transitionA2));
        x(transitionA, eVar);
    }

    public static void f(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, AbstractC0115G.a(obj));
    }

    public static void g(List list, View view) {
        int size = list.size();
        if (l(list, view, size)) {
            return;
        }
        list.add(view);
        for (int i2 = size; i2 < list.size(); i2++) {
            View view2 = (View) list.get(i2);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = viewGroup.getChildAt(i3);
                    if (!l(list, childAt, size)) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    public static Object h(Object obj, View view, ArrayList arrayList, Map map, View view2) {
        if (obj == null) {
            return obj;
        }
        i(arrayList, view);
        if (map != null) {
            arrayList.removeAll(map.values());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.add(view2);
        d(AbstractC0115G.a(obj), arrayList);
        return obj;
    }

    public static void i(ArrayList arrayList, View view) {
        if (view.getVisibility() == 0) {
            if (!(view instanceof ViewGroup)) {
                arrayList.add(view);
                return;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                i(arrayList, viewGroup.getChildAt(i2));
            }
        }
    }

    public static void j(View view, View view2, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3, Object obj4, ArrayList arrayList4, Map map) {
        Transition transitionA = AbstractC0115G.a(obj);
        Transition transitionA2 = AbstractC0115G.a(obj2);
        Transition transitionA3 = AbstractC0115G.a(obj3);
        Transition transitionA4 = AbstractC0115G.a(obj4);
        if (transitionA4 != null) {
            view.getViewTreeObserver().addOnPreDrawListener(new d(view, transitionA, arrayList, transitionA2, arrayList2, transitionA3, arrayList3, map, arrayList4, transitionA4, view2));
        }
    }

    public static Object k(Object obj) {
        return obj != null ? AbstractC0115G.a(obj).clone() : obj;
    }

    public static boolean l(List list, View view, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (list.get(i3) == view) {
                return true;
            }
        }
        return false;
    }

    public static void m(Object obj, Object obj2, Object obj3, ArrayList arrayList, boolean z2) {
        Transition transitionA = AbstractC0115G.a(obj);
        Transition transitionA2 = AbstractC0115G.a(obj2);
        Transition transitionA3 = AbstractC0115G.a(obj3);
        o(transitionA, transitionA3, arrayList, z2);
        o(transitionA2, transitionA3, arrayList, z2);
    }

    public static void n(Object obj, View view, boolean z2) {
        AbstractC0115G.a(obj).excludeTarget(view, z2);
    }

    public static void o(Transition transition, Transition transition2, ArrayList arrayList, boolean z2) {
        if (transition != null) {
            int size = transition2 == null ? 0 : arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                transition.excludeTarget((View) arrayList.get(i2), z2);
            }
        }
    }

    public static void p(Map map, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    p(map, viewGroup.getChildAt(i2));
                }
            }
        }
    }

    public static Rect q(View view) {
        Rect rect = new Rect();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        rect.set(i2, iArr[1], view.getWidth() + i2, iArr[1] + view.getHeight());
        return rect;
    }

    public static String r(View view) {
        return view.getTransitionName();
    }

    public static boolean s(Transition transition) {
        return (t(transition.getTargetIds()) && t(transition.getTargetNames()) && t(transition.getTargetTypes())) ? false : true;
    }

    public static boolean t(List list) {
        return list == null || list.isEmpty();
    }

    public static Object u(Object obj, Object obj2, Object obj3, boolean z2) {
        Transition transitionA = AbstractC0115G.a(obj);
        Transition transitionA2 = AbstractC0115G.a(obj2);
        Transition transitionA3 = AbstractC0115G.a(obj3);
        if (transitionA == null || transitionA2 == null) {
            z2 = true;
        }
        if (z2) {
            TransitionSet transitionSet = new TransitionSet();
            if (transitionA != null) {
                transitionSet.addTransition(transitionA);
            }
            if (transitionA2 != null) {
                transitionSet.addTransition(transitionA2);
            }
            if (transitionA3 == null) {
                return transitionSet;
            }
            transitionSet.addTransition(transitionA3);
            return transitionSet;
        }
        if (transitionA2 != null && transitionA != null) {
            transitionA = new TransitionSet().addTransition(transitionA2).addTransition(transitionA).setOrdering(1);
        } else if (transitionA2 != null) {
            transitionA = transitionA2;
        } else if (transitionA == null) {
            transitionA = null;
        }
        if (transitionA3 == null) {
            return transitionA;
        }
        TransitionSet transitionSet2 = new TransitionSet();
        if (transitionA != null) {
            transitionSet2.addTransition(transitionA);
        }
        transitionSet2.addTransition(transitionA3);
        return transitionSet2;
    }

    public static void v(Object obj, ArrayList arrayList) {
        List targets;
        Transition transitionA = AbstractC0115G.a(obj);
        if (Z.a(transitionA)) {
            TransitionSet transitionSetA = AbstractC0122a0.a(transitionA);
            int transitionCount = transitionSetA.getTransitionCount();
            for (int i2 = 0; i2 < transitionCount; i2++) {
                v(transitionSetA.getTransitionAt(i2), arrayList);
            }
            return;
        }
        if (s(transitionA) || (targets = transitionA.getTargets()) == null || targets.size() != arrayList.size() || !targets.containsAll(arrayList)) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            transitionA.removeTarget((View) arrayList.get(size));
        }
    }

    public static void w(Object obj, View view) {
        AbstractC0115G.a(obj).setEpicenterCallback(new a(q(view)));
    }

    public static void x(Transition transition, e eVar) {
        if (transition != null) {
            transition.setEpicenterCallback(new c(eVar));
        }
    }

    public static void y(Object obj, View view, Map map, ArrayList arrayList) {
        TransitionSet transitionSetA = AbstractC0122a0.a(obj);
        arrayList.clear();
        arrayList.addAll(map.values());
        List targets = transitionSetA.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            g(targets, (View) arrayList.get(i2));
        }
        arrayList.add(view);
        d(transitionSetA, arrayList);
    }

    public static Object z(Object obj) {
        Transition transitionA;
        if (obj == null || (transitionA = AbstractC0115G.a(obj)) == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(transitionA);
        return transitionSet;
    }
}
