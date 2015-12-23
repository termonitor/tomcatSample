package com.xiaoming.unit15.digestertest;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Created by panxiaoming on 15/12/23.
 */
public class EmployeeRuleSet extends RuleSetBase {
    @Override
    public void addRuleInstances(Digester digester) {
        digester.addObjectCreate("employee", "com.xiaoming.unit15.digestertest.Employee");
        digester.addSetProperties("employee");
        digester.addObjectCreate("employee/office", "com.xiaoming.unit15.digestertest.Office");
        digester.addSetProperties("employee/office");
        digester.addSetNext("employee/office", "addOffice");
        digester.addObjectCreate("employee/office/address", "com.xiaoming.unit15.digestertest.Address");
        digester.addSetProperties("employee/office/address");
        digester.addSetNext("employee/office/address", "setAddress");
    }
}
