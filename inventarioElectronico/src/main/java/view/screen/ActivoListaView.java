package view.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Activo;
import modelo.dao.impl.ActivoDAOimpl;

@SuppressWarnings("serial")
public class ActivoListaView extends JFrame {
    private JTextField txtBuscar;
    private JTable tablaActivos;
    private DefaultTableModel modeloTabla;

    public ActivoListaView() {
        setTitle("Lista de Activos - Inventario Electronico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar componentes
        txtBuscar = new JTextField(20);
        modeloTabla = new DefaultTableModel(new Object[]{"Tipo", "Marca", "Modelo", "Número de Serie", "Estado"}, 0);
        tablaActivos = new JTable(modeloTabla);

        // Diseño de la interfaz
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Buscar Activo:"), BorderLayout.WEST);
        panel.add(txtBuscar, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(tablaActivos), BorderLayout.CENTER);

        // Actualizar tabla al iniciar
        try {
            actualizarTabla("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Listener para la búsqueda
        txtBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarTabla(txtBuscar.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void actualizarTabla(String busqueda) throws SQLException {
        
        modeloTabla.setRowCount(0);
        ActivoDAOimpl activoDAO = new ActivoDAOimpl();
        List<Activo> activos = activoDAO.listarActivos(busqueda);

        // Agregar activos a la tabla
        for (Activo activo : activos) {
            modeloTabla.addRow(new Object[]{activo.getTipo(), activo.getMarca(), activo.getModelo(), activo.getNumeroSerie(), activo.getEstado()});
        }
    }

   
}
