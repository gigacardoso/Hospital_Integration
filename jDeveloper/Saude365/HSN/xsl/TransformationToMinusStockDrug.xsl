<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../DBPharmacy.wsdl"/>
      <rootElement name="PharmacyCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/DBPharmacy"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../DBPharmacy.wsdl"/>
      <rootElement name="PharmacyCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/DBPharmacy"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.6.0(build 111214.0600.1553) AT [SUN APR 28 12:41:01 WEST 2013]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/db/Saude365/HSN/DBPharmacy"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/DBPharmacy"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl tns plt xsd wsdl top bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:template match="/">
    <top:PharmacyCollection>
      <top:Pharmacy>
        <top:drugname>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:drugname"/>
        </top:drugname>
        <top:dosage>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:dosage"/>
        </top:dosage>
        <top:substance>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:substance"/>
        </top:substance>
        <top:stock>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:stock + -(1.0)"/>
        </top:stock>
        <top:stockLine>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:stockLine"/>
        </top:stockLine>
        <top:orderSize>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:orderSize"/>
        </top:orderSize>
        <top:price>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:price"/>
        </top:price>
        <top:orderInplace>
          <xsl:value-of select="/top:PharmacyCollection/top:Pharmacy/top:orderInplace"/>
        </top:orderInplace>
      </top:Pharmacy>
    </top:PharmacyCollection>
  </xsl:template>
</xsl:stylesheet>
