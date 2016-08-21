
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



enum EntryType{
        Infija,Prefija,Postfija;
  }

public class Calculadora {

    /**
     * @param args the command line arguments
     */
    private String optionsTitle[] = {"Infija","Prefija","Postfija"};
    private EntryType entryType = EntryType.Infija;
    private ActionListener changeEntryTypeListener,executeListener;
    /*UI*/
    JRadioButton optionsRadioButton [];
    JFrame window;
    JTextField operationTextField;
    JButton executeButton;
    
    public Calculadora() {
        configListeners();
        generateUI();
    }
    
    
    public void configListeners(){
        changeEntryTypeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton object = (JRadioButton)e.getSource();
                if( object ==  optionsRadioButton[0]){
                    entryType = EntryType.Infija;
                }else if ( object ==  optionsRadioButton[1]){
                    entryType = EntryType.Prefija;
                }else if ( object ==  optionsRadioButton[2]){
                    entryType = EntryType.Postfija;
                }
            }
        };
        
        executeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String infija = "";
                String prefija = "";
                String postfija = "";
                
                switch(entryType){
                    case Infija:
                        infija = operationTextField.getText();
                        prefija = generatePrefija();
                        postfija = generatePostfija();
                    case Prefija:
                    case Postfija:
                }
                
                PrintWriter writer;
                try {
                    writer = new PrintWriter("resultado.txt", "UTF-8");
                    writer.println("Infija");
                    writer.println(infija);
                    writer.println("Prefija");
                    writer.println(prefija);
                    writer.println("Postfija");
                    writer.println(postfija);
                    writer.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("No se encontro el archivo");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("No se soporta la codificaion UTF-8");
                }
            
            }
        };
    }
    
    public void generateUI(){
        window = new JFrame("Proyecto Java");
        Container container = window.getContentPane();
	container.setLayout(new GridLayout(5, 1));
        operationTextField = new JTextField();
        optionsRadioButton = new JRadioButton[3];
        ButtonGroup optionsGroup = new ButtonGroup();
        
        for (int i = 0; i < 3; i++) {
            optionsRadioButton[i] = new JRadioButton(optionsTitle[i]);
            optionsRadioButton[i].addActionListener(changeEntryTypeListener);
            optionsGroup.add(optionsRadioButton[i]);
        }
        optionsRadioButton[0].setSelected(true);
        
        executeButton = new JButton("Generar");
        executeButton.addActionListener(executeListener);
        container.add(operationTextField);
        container.add(optionsRadioButton[0]);
        container.add(optionsRadioButton[1]);
        container.add(optionsRadioButton[2]);
        container.add(executeButton);
        window.setSize(500, 200);
        //window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Stack generateInfija(){
        Stack Stack = null;
        return Stack;
    }
    public String generatePrefija(){
        InfijoAOtros o = new InfijoAOtros();
        String prefix = o.getPrefix(operationTextField.getText());
        return prefix;
    }
    
    public String generatePostfija(){
        InfijoAOtros o = new InfijoAOtros();
        String postfix = o.getPostfix(operationTextField.getText());
        return postfix;
    }     

    
}
