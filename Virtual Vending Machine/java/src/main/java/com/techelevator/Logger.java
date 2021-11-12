package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Logger {

    private File auditFile;
    private boolean appendMode;
    private PrintWriter auditWriter;
    private LocalDate auditDate = LocalDate.from(LocalDateTime.now());

/*  LOGGER ISSUE:
    Logger will track first event to occur during application run time
    but fails to log any subsequent event after that in the same
    application loop*/

    public Logger() {

        try {
            this.auditFile = new File("log.txt");
            this.appendMode = true;
            this.auditWriter = new PrintWriter(new FileOutputStream(auditFile, appendMode));
        } catch (FileNotFoundException fnf){
            System.out.println("No auditing! Take what you can get!");
        }
    }

    public void log(String event){

        String[] eventBreakDown = event.split(",");
        String transactionType = eventBreakDown[0];
        String startingMoney = eventBreakDown[1];
        String endingMoney = eventBreakDown[2];

        DateFormat formatter= new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date date = new Date(System.currentTimeMillis());

        auditWriter.println(formatter.format(date) + " " + transactionType + ": $" + startingMoney  + " $" + endingMoney);
        auditWriter.close();
    }
}
