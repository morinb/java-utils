package org.bm.utils.swing.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.LinkedList;
import java.util.List;

public class DraggableFlowLayout implements LayoutManager2 {

    public static int LEFT = 0x1 << 0;
    public static int TOP = 0x1 << 1;

    private List<Component> components = new LinkedList<>();

    private int hgap = 0, vgap = 0;
    private int align;

    public DraggableFlowLayout(int align, int hgap, int vgap) {
        this.align = align;
        this.hgap = hgap;
        this.vgap = vgap;
    }

    public DraggableFlowLayout() {
        this(LEFT, 0, 0);
    }

    /**
     * Called by the Container class's add methods. Layout managers that do not
     * associate strings with their components generally do nothing in this
     * method.
     */
    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    /**
     * Called to position and size each of the components in the container. A
     * layout manager's layoutContainer method does not actually draw
     * components. It simply invokes one or more of each component's setSize,
     * setLocation, and setBounds methods to set the component's size and
     * position.
     */
    @Override
    public void layoutContainer(Container parent) {
        int componentCount = components.size();
        int width = parent.getWidth();
        int height = parent.getHeight();

        int x = hgap;
        int y = vgap;

        for (int i = 0; i < componentCount; i++) {
            Component component = components.get(i);
            if (component.isVisible()) {
                component.setLocation(x, y);
                component.setSize(component.getPreferredSize());

                if (align == LEFT) {

                    int x2 = x + component.getWidth();

                    if (x2 + hgap > width) {
                        x = hgap;
                        y += component.getHeight() + vgap;
                        component.setLocation(x, y);
                    }
                    x += component.getWidth() + hgap;
                } else if (align == TOP) {
                    int y2 = y + component.getHeight();
                    if(y2 + vgap > height) {
                        y = vgap;
                        x += component.getWidth() + hgap;
                        component.setLocation(x, y);
                    }
                    y += component.getHeight() + vgap;
                }
            }
        }

    }

    /**
     * Called by the Container getMinimumSize method, which is itself called
     * under a variety of circumstances. This method should calculate and return
     * the minimum size of the container, assuming that the components it
     * contains will be at or above their minimum sizes. This method must take
     * into account the container's internal borders, which are returned by the
     * getInsets method.
     */
    @Override
    public Dimension minimumLayoutSize(Container target) {

        return preferredLayoutSize(target);
    }

    /**
     * Called by the Container class's getPreferredSize method, which is itself
     * called under a variety of circumstances. This method should calculate and
     * return the ideal size of the container, assuming that the components it
     * contains will be at or above their preferred sizes. This method must take
     * into account the container's internal borders, which are returned by the
     * {@link Container#getInsets} method.
     */
    @Override
    public Dimension preferredLayoutSize(Container target) {

        synchronized (target.getTreeLock()) {

            int maxW = 0;
            int maxH = 0;

            int nmembers = target.getComponentCount();

            for (int i = 0; i < nmembers; i++) {
                Component component = components.get(i);
                if (component.isVisible()) {
                    int x2 = component.getX() + component.getWidth();
                    int y2 = component.getY() + component.getHeight();

                    maxW = Math.max(maxW, x2 + hgap);
                    maxH = Math.max(maxH, y2 + vgap);

                }
            }
            return new Dimension(maxW, maxH);

        }
    }

    /**
     * Called by the Container methods remove and removeAll. Layout managers
     * override this method to clear an internal state they may have associated
     * with the Component.
     */
    @Override
    public void removeLayoutComponent(Component parent) {
        components.remove(parent);
        invalidateLayout(parent.getParent());
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        components.add(comp);
        invalidateLayout(comp.getParent());
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }

}
