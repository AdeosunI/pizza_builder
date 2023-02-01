// This is starter code for Lab10, SP2022, CSc1302, Dr. William Johnson

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class PizzaBuilder {
    private static JFrame myFrame = new JFrame("Build a Pizza");

    private static JRadioButton small, medium, large;
    private static ButtonGroup bg;
    private static JLabel sizeList = new JLabel("Sizes List: ");
    private static JLabel Price = new JLabel("Total $ ");
    private static JLabel totPrice = new JLabel("0.0");
    private static JLabel topsPrice = new JLabel("Topping $ ");
    private static JLabel toppingsPrice = new JLabel("0.0");

    private static final double LARGE=16.49;
    private static final double MEDIUM=13.49;
    private static final double SMALL=10.49;
    private static final double MEATITEM=2.25;
    private static final double VEGITEM=1.75;

    private static int meattopping=0;
    private static int vegtopping=0;
    private static JCheckBox sausage, pepperoni, canadian_ham, anchovies;
    private static JCheckBox mushroom, green_pepper, onion, black_olive;

    public static void main(String[] args) {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new FlowLayout());

        JPanel panelSizePrice = makeSizePricePanel();
        JPanel panelCheck = makePizzaPanel();

        myFrame.add(panelSizePrice);
        myFrame.add(panelCheck);
        myFrame.setSize(620,340);
        myFrame.setVisible(true);
    }

    private static JPanel makeSizePricePanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(2,4));
        jp.setPreferredSize(new Dimension(575,100));

        small = new JRadioButton("Small", true);
        medium = new JRadioButton("Medium", false);
        large = new JRadioButton("Large", false);
        totPrice.setText(String.valueOf(SMALL));
        //add the other radiobuttons

        small.addItemListener(new pizzaSizeListen());
        medium.addItemListener(new pizzaSizeListen());
        large.addItemListener(new pizzaSizeListen());

        //set each one to the listener: pizzaSizeListen()
        //it is an ItemListener, not ActionListener

        bg = new ButtonGroup();
        bg.add(small);
        bg.add(medium);
        bg.add(large);
        jp.add(sizeList);
        jp.add(small);
        jp.add(medium);
        jp.add(large);
        jp.add(Price);
        jp.add(totPrice);
        jp.add(topsPrice);
        jp.add(toppingsPrice);

        // add the radio buttons to the button group
        // add the sizelist, radio buttons, Price, totPrice, topsPrice
        // and toppingsPrice to the JPanel
        // order is VERY important

        return jp;

    }
    private static class pizzaSizeListen implements ItemListener {
        public void itemStateChanged(ItemEvent i)
        {
            double sizePrice = SMALL;
            if (i.getItem()==large)sizePrice=LARGE;
            else if (i.getItem()==medium)sizePrice=MEDIUM;
            else if (i.getItem()==small)sizePrice=SMALL;

            //check which radio button was 'selected' and set the price
            //according to the constants, SMALL, MEDIUM, LARGE

            double pizzaPrice = sizePrice + Double.parseDouble(toppingsPrice.getText());
            totPrice.setText(String.valueOf(pizzaPrice));
        }
    }
    private static JPanel makePizzaPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(2,4));
        jp.setPreferredSize(new Dimension(575,100));

        sausage= new JCheckBox("Sauage");
        pepperoni= new JCheckBox("Pepperoni");
        canadian_ham= new JCheckBox("Canadian ham");
        anchovies= new JCheckBox("Anchovies");
        black_olive= new JCheckBox("Black olive");
        onion= new JCheckBox("Onion");
        mushroom= new JCheckBox("Mushroom");
        green_pepper= new JCheckBox("Green pepper");

        sausage.addItemListener(new pizzaToppingListen());
        pepperoni.addItemListener(new pizzaToppingListen());
        canadian_ham.addItemListener(new pizzaToppingListen());
        anchovies.addItemListener(new pizzaToppingListen());
        black_olive.addItemListener(new pizzaToppingListen());
        onion.addItemListener(new pizzaToppingListen());
        mushroom.addItemListener(new pizzaToppingListen());
        green_pepper.addItemListener(new pizzaToppingListen());

        jp.add(sausage);
        jp.add(pepperoni);
        jp.add(canadian_ham);
        jp.add(anchovies);
        jp.add(black_olive);
        jp.add(onion);
        jp.add(mushroom);
        jp.add(green_pepper);

        //make all the checkbox for each topping
        //set each one to the listener: pizaToppingListen()
        //add the checkboxes to the JPanel, meat first, then
        //veggies second

        Border blackline = BorderFactory.createLineBorder(Color.black);
        jp.setBorder(blackline);
        return jp;

    }
    private static class pizzaToppingListen implements ItemListener {
        public void itemStateChanged(ItemEvent i) {

            //check if each checkBox is selected, then
            // if state==1, then increment meattopping or vegtopping
            // else decrement meattopping or vegtopping

            if(i.getItem()==sausage || i.getItem()==pepperoni || i.getItem()==canadian_ham || i.getItem()==anchovies){
                if (i.getStateChange()==1)meattopping++;
                else meattopping--;
            } else{
                if (i.getStateChange()==1)vegtopping++;
                else vegtopping--;
            }

            double topsPrice = (meattopping * MEATITEM)+(vegtopping * VEGITEM);
            double sizePrice=0.0;
            if(small.isSelected()) {
                totPrice.setText(String.valueOf(SMALL));
                sizePrice=SMALL;
            }
            if(medium.isSelected()) {
                totPrice.setText(String.valueOf(MEDIUM));
                sizePrice=MEDIUM;
            }
            if(large.isSelected()) {
                totPrice.setText(String.valueOf(LARGE));
                sizePrice=LARGE;
            }

            toppingsPrice.setText(String.valueOf(topsPrice));
            totPrice.setText(String.valueOf(topsPrice + sizePrice));
        }
    }

}