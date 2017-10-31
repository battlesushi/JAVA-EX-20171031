import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private LoginFrame loginFrame=new LoginFrame();
    private JMenuBar jmb=new JMenuBar();
    private JMenu jmF=new JMenu("File");
    private JMenu jmSet=new JMenu("Set");
    private JMenu jmGame=new JMenu("Game");
    private JMenu jmAbout=new JMenu("About");
    private JMenuItem jMenuItemExit=new JMenuItem("Exit");
    private JMenuItem jMenuItemFont=new JMenuItem("Font");
    private JMenuItem jMenuItemLoto=new JMenuItem("Loto");
    private JDesktopPane jdp=new JDesktopPane();//用不到cp的話
    private JInternalFrame jInternalFrame=new JInternalFrame();
    private Container jifCP;
    private JPanel jpn=new JPanel(new GridLayout(1,6,5,5));//放樂透亂數
    private JPanel jpn2=new JPanel(new GridLayout(1,2,5,5));//放功能
    private JPanel jpnFont=new JPanel(new GridLayout(2,3,5,5));//jpnFont
    private JLabel jlb[]=new JLabel[6];
    private JLabel jlbFfamily=new JLabel("Family");
    private JLabel jlbFstyle=new JLabel("Style");
    private JLabel jlbFsize=new JLabel("Size");
    private String option[]={"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcbStyle=new JComboBox(option);
    private JTextField jtffamily=new JTextField("Times new Romen");
    private JTextField jtfSize=new JTextField("20");
    private JButton jbtnClose=new JButton("Close");
    private JButton jbtnRe=new JButton("Re");
    private int check[]=new int[6];
    private Random rmd=new Random(System.currentTimeMillis());

    public MainFrame(LoginFrame login){
        init();
        loginFrame=login;
    }
    public void init(){
        this.setBounds(300,300,500,400);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);//用不到cp的話
        jmb.add(jmF);
        jmb.add(jmSet);
        jmb.add(jmGame);
        jmb.add(jmAbout);
        jmF.add(jMenuItemExit);
        jmSet.add(jMenuItemFont);
        jmGame.add(jMenuItemLoto);
        jpnFont.add(jlbFfamily);
        jpnFont.add(jlbFstyle);
        jpnFont.add(jlbFsize);
        jpnFont.add(jtffamily);
        jpnFont.add(jcbStyle);
        jpnFont.add(jtfSize);
        jInternalFrame.setBounds(0,0,200,80);
        jifCP=jInternalFrame.getContentPane();
        jInternalFrame.setLayout(new BorderLayout(5,5));
        jifCP.add(jpn,BorderLayout.CENTER);
        jifCP.add(jpn2,BorderLayout.SOUTH);
        jpn2.add(jbtnClose);
        jpn2.add(jbtnRe);
        jMenuItemFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result=JOptionPane.showConfirmDialog(MainFrame.this,jpnFont,"Font Setting",JOptionPane.OK_CANCEL_OPTION);
                int fontstyle=0;
                switch (jcbStyle.getSelectedIndex()){
                    case 0:
                        fontstyle=Font.PLAIN;
                        break;
                    case 1:
                        fontstyle=Font.BOLD;
                        break;
                    case 2:
                        fontstyle=Font.ITALIC;
                        break;
                    case 3:
                        fontstyle=Font.BOLD+Font.ITALIC;
                        break;
                }
                if(result==JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(jtffamily.getText(),fontstyle,Integer.parseInt(jtfSize.getText())));
                }
            }
        });
        jMenuItemLoto.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        for(int i=0;i<jlb.length;i++){
            jlb[i]=new JLabel(Integer.toString(i));
            jpn.add(jlb[i]);
        }
        lotoGenerate();
        jMenuItemLoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
            }
        });
        jbtnRe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotoGenerate();
            }
        });
        jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.dispose();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrame.reset();
                loginFrame.setVisible(true);
            }
        });
    }
    private void lotoGenerate(){//重複亂數處理
        int i=0;
        while(i<6){
            check[i]=rmd.nextInt(42)+1;
            int j=0;
            boolean flag=true;
            while(j<i && flag){
                if(check[i]==check[j]){ //若亂數重複 則不放入jlb[]
                    flag=false;
                }
                j++;   //true 則往下一個亂數去比對  false則跑出去 重新跑迴圈從j=0
            }
            if(flag){
                jlb[i].setText(Integer.toString(check[i]));//不放入jlb[]  flag=false
                i++;
            }
        }
    }


}
