<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:variable name="reqId" select="//request/@reqId" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Edit Phone</title>
            </head>
            <body>
                <h3>Edit Phone</h3>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>


    <xsl:template match="contact">
        <form method="post">
            <input type="text" name="phone" value="{phone}"/>
            <input type="hidden" name="reqId" value="{$reqId}" />
            <button type="submit">Next</button>
        </form>
    </xsl:template>

</xsl:stylesheet>