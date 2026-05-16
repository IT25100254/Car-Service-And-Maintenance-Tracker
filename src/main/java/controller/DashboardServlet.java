package controller;

import model.PDFReport;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String reportId = "RPT-" + (System.currentTimeMillis() % 1000);
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        
        String vehicleNo = request.getParameter("vehicleNo");
        String costStr = request.getParameter("totalCost");

        double totalCost = 0.0;
        if (costStr != null && !costStr.isEmpty()) {
            totalCost = Double.parseDouble(costStr);
        }

        PDFReport pdfReport = new PDFReport(reportId, date, vehicleNo, totalCost);
        String reportData = pdfReport.export(); 

        String filePath = getServletContext().getRealPath("/") + "WEB-INF/classes/reports.txt";
        File file = new File(filePath);
        
        try (FileWriter fw = new FileWriter(file, true); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(reportData);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("reportId", reportId);
        request.setAttribute("date", date);
        request.setAttribute("vehicleNo", vehicleNo);
        request.setAttribute("totalCost", totalCost);

        request.getRequestDispatcher("report_preview.jsp").forward(request, response);
    }
}
