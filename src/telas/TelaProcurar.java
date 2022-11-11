package telas;

import bd.MySQL;
import beans.Cliente;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.TelaMenu;

public class TelaProcurar extends JFrame {
    
    private Cliente cliente;
    private MySQL conexao;
    private TelaMenu parent;
    
    public TelaProcurar(TelaMenu parent) {
        this.parent = parent;
        initComponents();
        cliente = new Cliente();
        conexao = new MySQL("localhost:3306", 
                "clientes", 
                "root",
                "Amora250616@");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void buscar(Cliente cliente) throws SQLException{
        conexao.conectaBanco();
        
        String cpf = this.txtCpf.getText();
        
        String buscar_dado = 
                "SELECT "
                + "CPF, Nome, Telefone, Cidade, UF, Email "                                
                + "FROM "
                + "cliente "
                + "WHERE "
                + "CPF = '" + cpf + "'"
                + ";";
        
        if (cpf == null || cpf.isBlank() || cpf.equals("   .   .   -  ")){
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira o CPF do cliente desejado.",
                    "Informações obrigatórias não preenchidas", JOptionPane.ERROR_MESSAGE);          
        } else{
            try {
                conexao.executarSQL(buscar_dado);
                cliente.limpar_cliente();
                while(conexao.getResultSet().next()){  
                    cliente.setCpf(this.conexao.getResultSet().getString(1));
                    cliente.setNome(this.conexao.getResultSet().getString(2));
                    cliente.setTelefone(this.conexao.getResultSet().getString(3));
                    cliente.setCidade(this.conexao.getResultSet().getString(4));
                    cliente.setUf(this.conexao.getResultSet().getString(5));
                    cliente.setEmail(this.conexao.getResultSet().getString(6));
            }
                if (cliente.getNome().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Esse cliente não existe!");
                }
                
             } catch (HeadlessException | SQLException e) {            
                System.out.println("Erro ao consultar cliente! " +  e.getMessage());
                JOptionPane.showMessageDialog(null, "Falha em buscar cliente!");
            
            } finally{
                txtCpf.setText(cliente.getCpf());
                txtNome.setText(cliente.getNome());
                txtTelefone.setText(cliente.getTelefone());
                txtCidade.setText(cliente.getCidade());
                txtUf.setText(cliente.getUf());
                txtEmail.setText(cliente.getEmail());

                conexao.fechaBanco();   
            }                      
        }
    }
    
    private void deletar(){
        conexao.conectaBanco();
        
        String cpf = this.txtCpf.getText();
        
        String deletar_dado =
                "DELETE FROM cliente "
                + "WHERE "
                + "cpf = '" + cpf + "';";
        
        if (txtNome.getText() == null || txtNome.getText().isBlank() ||
            txtCpf.getText() == null || txtCpf.getText().isBlank() ||
            txtTelefone.getText() == null || txtTelefone.getText().isBlank() ||
            txtCidade.getText() == null || txtCidade.getText().isBlank() ||
            txtUf.getText() == null || txtUf.getText().isBlank() ||
            txtEmail.getText() == null || txtEmail.getText().isBlank()){
                
            JOptionPane.showMessageDialog(this,
                    "Há informações faltando ou nenhum cliente foi selecionado.",
                    "Informações Incorretas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            conexao.updateSQL(deletar_dado);            
        } catch (Exception e) {
            System.out.println("Falha ao deletar cliente! " +  e.getMessage());
            JOptionPane.showMessageDialog(null, "Falha ao deletar cliente");
        } finally{
            conexao.fechaBanco();
            limpar_dados();
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");            
        } 
    }
    
    private void alterar(){
        conexao.conectaBanco();
        
        String cpf = this.txtCpf.getText();
        
        String alterar_dado = 
                "UPDATE cliente SET "                    
                + "Nome = '" + txtNome.getText() + "',"
                + "Telefone = '" + txtTelefone.getText() + "',"
                + "Cidade = '" + txtCidade.getText() + "',"                   
                + "UF = '" + txtUf.getText() + "',"
                + "Email = '" + txtEmail.getText() + "' "
                + "WHERE "
                + "cpf = '" + cpf + "';";   
        
        if (txtNome.getText() == null || txtNome.getText().isBlank() ||
            txtCpf.getText() == null || txtCpf.getText().isBlank() ||
            txtTelefone.getText() == null || txtTelefone.getText().isBlank() ||
            txtCidade.getText() == null || txtCidade.getText().isBlank() ||
            txtUf.getText() == null || txtUf.getText().isBlank() ||
            txtEmail.getText() == null || txtEmail.getText().isBlank()){
                
            JOptionPane.showMessageDialog(this,
                    "Por favor, digite todas as informações. Todos os campos são obrigatórios!",
                    "Informações obrigatórias não preenchidas", JOptionPane.ERROR_MESSAGE);
            return;
        }
            
        try{
            conexao.updateSQL(alterar_dado);
        }catch(Exception e){
            System.out.println("Erro ao atualizar cliente " +  e.getMessage());
            JOptionPane.showMessageDialog(null, "Falha ao atualizar cliente!");
        } finally {
            conexao.fechaBanco();
            limpar_dados();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        }
    }
    
    private void limpar_dados(){
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtCidade.setText("");
        txtUf.setText("");
        txtEmail.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        bttnLimpar = new javax.swing.JButton();
        bttnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtUf = new javax.swing.JTextField();
        bttnDeletar = new javax.swing.JButton();
        bttnAlterar = new javax.swing.JButton();
        txtTelefone = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(319, 39));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Busca");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });

        jLabel3.setText("CPF");

        bttnLimpar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bttnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar.png"))); // NOI18N
        bttnLimpar.setText("Limpar");
        bttnLimpar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bttnLimpar.setMaximumSize(new java.awt.Dimension(98, 26));
        bttnLimpar.setMinimumSize(new java.awt.Dimension(98, 26));
        bttnLimpar.setPreferredSize(new java.awt.Dimension(98, 26));
        bttnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttnLimparMouseClicked(evt);
            }
        });
        bttnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnLimparActionPerformed(evt);
            }
        });

        bttnBuscar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        bttnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/procurar2.png"))); // NOI18N
        bttnBuscar.setText("Buscar ");
        bttnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bttnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttnBuscarMouseClicked(evt);
            }
        });
        bttnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnBuscarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nome");

        jLabel4.setText("Celular");

        jLabel5.setText("Cidade");

        jLabel6.setText("UF");

        jLabel7.setText("Email");

        bttnDeletar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bttnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/remover.png"))); // NOI18N
        bttnDeletar.setText("Deletar");
        bttnDeletar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bttnDeletar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttnDeletarMouseClicked(evt);
            }
        });

        bttnAlterar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bttnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar2.png"))); // NOI18N
        bttnAlterar.setText("Alterar");
        bttnAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bttnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttnAlterarMouseClicked(evt);
            }
        });
        bttnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAlterarActionPerformed(evt);
            }
        });

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(bttnAlterar)
                .addGap(65, 65, 65)
                .addComponent(bttnDeletar)
                .addGap(85, 85, 85))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                                .addComponent(jLabel5)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(txtUf, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bttnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bttnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void bttnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttnLimparMouseClicked
        limpar_dados();
    }//GEN-LAST:event_bttnLimparMouseClicked

    private void bttnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnLimparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttnLimparActionPerformed

    private void bttnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttnBuscarMouseClicked
        try {
            buscar(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(TelaProcurar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bttnBuscarMouseClicked

    private void bttnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttnBuscarActionPerformed

    private void bttnDeletarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttnDeletarMouseClicked
        deletar();
    }//GEN-LAST:event_bttnDeletarMouseClicked

    private void bttnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttnAlterarMouseClicked
        alterar();
    }//GEN-LAST:event_bttnAlterarMouseClicked

    private void bttnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAlterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttnAlterarActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnAlterar;
    private javax.swing.JButton bttnBuscar;
    private javax.swing.JButton bttnDeletar;
    private javax.swing.JButton bttnLimpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtUf;
    // End of variables declaration//GEN-END:variables
}
