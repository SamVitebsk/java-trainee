package com.andersen.behavioral.chainOfResponsibility;

public class Main {
    public static void main(String[] args) {
        SimpleReportNotifier simpleNotifier = new SimpleReportNotifier(Priority.ROUTINE);
        EmailNotifier emailNotifier = new EmailNotifier(Priority.IMPORTANT);
        SMSNotifier smsNotifier = new SMSNotifier(Priority.ASAP);

        simpleNotifier.setNextNotifier(emailNotifier);
        emailNotifier.setNextNotifier(smsNotifier);

        simpleNotifier.notifyManage("Routine message", Priority.ROUTINE);
        simpleNotifier.notifyManage("Important message", Priority.IMPORTANT);
        simpleNotifier.notifyManage("Asap message", Priority.ASAP);
    }
}
