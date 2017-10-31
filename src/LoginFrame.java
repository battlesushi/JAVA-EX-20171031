import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    private Container cp;
    private JLabel jlbID=new JLabel("ID:");
    private JLabel jlbPW=new JLabel("Password:");
    private JTextField jtfID=new JTextField();
    private JPasswordField jtfPW=new JPasswordField();
    private JButton jbtnLogin=new JButton("Login");
    private JButton jbtnExit=new JButton("Exit");
    public LoginFrame(){
        init();
    }
    private void init(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300,300,300,200);
        this.setLocationRelativeTo(null);
        cp=this.getContentPane();
        cp.setLayout(new GridLayout(3,2,3,3));
        cp.add(jlbID);
        jlbID.setHorizontalAlignment(jlbID.RIGHT);
        cp.add(jtfID);
        cp.add(jlbPW);
        jlbPW.setHorizontalAlignment(jlbID.RIGHT);
        cp.add(jtfPW);
        cp.add(jbtnExit);
        cp.add(jbtnLogin);
        jbtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtfID.getText().equals("h304") &&  new String(jtfPW.getPassword()).equals("23323456")){
                    MainFrame mFrm=new MainFrame(LoginFrame.this);
                    mFrm.setVisible(true);
                    LoginFrame.this.setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(LoginFrame.this,"Error");
            }
        });
        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public void reset(){
        jtfID.setText("");
        jtfPW.setText("");
    }
}