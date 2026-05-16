<!DOCTYPE html>
<html>
<head>
    <title>PDF Report Preview</title>
    <style>
        .report-card { max-width: 550px; margin: 40px auto; padding: 30px; border: 2px dashed #444; font-family: monospace; background-color: #fafafa; }
        .title { text-align: center; font-size: 22px; font-weight: bold; }
        .badge { color: white; background-color: #28a745; padding: 4px 8px; font-size: 12px; border-radius: 3px; }
    </style>
</head>
<body>
    <div class="report-card">
        <div class="title">MAINTENANCE REPORT PREVIEW</div>
        <hr>
        <p><strong>Report ID:</strong> <%= request.getAttribute("reportId") %></p>
        <p><strong>Generated Date:</strong> <%= request.getAttribute("date") %></p>
        <p><strong>Vehicle Number:</strong> <%= request.getAttribute("vehicleNo") %></p>
        <hr>
        <h3>Cost Summary:</h3>
        <p>Total Estimated Expense: <strong>Rs. <%= request.getAttribute("totalCost") %>0</strong></p>
        <hr>
        <span class="badge">Status: Logged to reports.txt</span>
    </div>
</body>
</html>
