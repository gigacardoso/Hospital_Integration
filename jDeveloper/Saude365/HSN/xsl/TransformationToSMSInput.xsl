<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../Order.wsdl"/>
      <rootElement name="order" namespace="http://saude365.pt"/>
    </source>
    <source type="WSDL">
      <schema location="../PatientsDB.wsdl"/>
      <rootElement name="PatientCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/PatientsDB"/>
      <param name="PatientNumber.PatientCollection" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../wsdl/PatientNotificationService.wsdl"/>
      <rootElement name="notifyPatient" namespace="http://patientnotification/"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.6.0(build 111214.0600.1553) AT [TUE APR 30 16:55:05 WEST 2013]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/PatientsDB"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/Saude365/HSN/PatientsDB"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ns1="http://patientnotification/"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/Saude365/HSN/Order"
                xmlns:imp1="http://saude365.pt"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl top ns0 pc plt wsdl jca xsd tns imp1 soap12 soap mime ns1 xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:param name="PatientNumber.PatientCollection"/>
  <xsl:template match="/">
    <ns1:notifyPatient>
      <arg0>
        <xsl:value-of select="$PatientNumber.PatientCollection/top:PatientCollection/top:Patient/top:name"/>
      </arg0>
      <arg1>
        <xsl:value-of select="$PatientNumber.PatientCollection/top:PatientCollection/top:Patient/top:phoneNumber"/>
      </arg1>
      <arg2>
        <xsl:value-of select="/imp1:order/imp1:nome"/>
      </arg2>
    </ns1:notifyPatient>
  </xsl:template>
</xsl:stylesheet>
