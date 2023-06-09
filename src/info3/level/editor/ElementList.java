package info3.level.editor;

import java.util.ArrayList;
import java.util.List;

import java.awt.Graphics;
import java.awt.Window;
import java.io.IOException;

public class ElementList {
    List<ElementContainer> elems ;
    int x ;
    int y ;
    final int SCALE = 2 ;

    public ElementList(int x, int y) throws IOException {
        this.x = x ;
        this.y = y ;

        fillElems(); 
    }
    
    void fillElems() throws IOException {
        elems = new ArrayList<ElementContainer>();
        elems.add(new ElementContainer(new VoidBlock(), 0, 0));
        elems.add(new ElementContainer(new Block1(), 0, 1));
    }
    public ElementContainer select(int x, int y) {
        int i = 0 ;
        for (ElementContainer elem : elems) {
            if ((Element.imageRealSize(SCALE)*i < x) && (x < (i+1)*Element.imageRealSize(SCALE))) {
                System.out.println("Selected " + elem.toString());
                return elem ;
            }
            i++ ;
        }
        return null ;
    }

    void paint(Graphics g) {
        Graphics g2 = g.create(x, y, getRealWidth(), getRealHeight());
        int i = 0 ;
        for (ElementContainer elem : elems) {
            elem.paint(g2, i, 0, SCALE);
            i++ ;
        }
    }

    public int getRealWidth() {
        return SCALE*Element.DEFAULT_SIZE*elems.size() + 1 ;
    }
    public int getRealHeight() {
        return SCALE*Element.DEFAULT_SIZE + 1 ;
    }

    public void tick(long elapsed) {
        int winWidth = Window.getWindows()[0].getWidth();
        x = winWidth - getRealWidth() - 40 ;

        int winHeight = Window.getWindows()[0].getHeight();
        y = winHeight - getRealHeight() - 100 ;
    }

}
