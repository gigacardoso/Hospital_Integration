<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../ReadPatientData.wsdl"/>
      <rootElement name="relatorio" namespace="http://saude365.pt"/>
    </source>
    <source type="WSDL">
      <schema location="../wsdl/InfarmedService.wsdl"/>
      <rootElement name="validateDrugs" namespace="http://infarmedvalidate/"/>
      <param name="Drug.parameters" />
    </source>
    <source type="WSDL">
      <schema location="../wsdl/InfarmedService.wsdl"/>
      <rootElement name="validateDrugsResponse" namespace="http://infarmedvalidate/"/>
      <param name="DrugCheckOutput.parameters" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../DrugDatabase.wsdl"/>
      <rootElement name="DrugsCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/DrugDatabase"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.6.0(build 111214.0600.1553) AT [FRI MAR 22 13:05:30 WET 2013]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/DrugDatabase"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/db/Saude365/HSN/DrugDatabase"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/Saude365/HSN/ReadPatientData"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:ns0="http://infarmedvalidate/"
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
                xmlns:imp1="http://saude365.pt"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl pc soap12 plt soap mime wsdl tns ns0 jca xsd imp1 top ns1 xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:param name="Drug.parameters"/>
  <xsl:param name="DrugCheckOutput.parameters"/>
  <xsl:template match="/">
    <top:DrugsCollection>
      <top:Drugs>
        <top:dateVisit>
          <xsl:value-of select="/imp1:relatorio/imp1:dataHora"/>
        </top:dateVisit>
        <top:patientId>
          <xsl:value-of select="/imp1:relatorio/imp1:doente/imp1:id"/>
        </top:patientId>
        <top:drugname>
          <xsl:value-of select="$Drug.parameters/ns0:validateDrugs/arg1"/>
        </top:drugname>
        <top:dosage>
          <xsl:value-of select="$Drug.parameters/ns0:validateDrugs/arg2"/>
        </top:dosage>
        <top:substance>
          <xsl:value-of select="$Drug.parameters/ns0:validateDrugs/arg0"/>
        </top:substance>
        <top:drugValid>
          <xsl:value-of select="$DrugCheckOutput.parameters/ns0:validateDrugsResponse/return"/>
        </top:drugValid>
      </top:Drugs>
    </top:DrugsCollection>
  </xsl:template>
</xsl:stylesheet>
