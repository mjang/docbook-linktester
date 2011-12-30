/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2011 ForgeRock AS. All Rights Reserved
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * http://forgerock.org/license/CDDLv1.0.html
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at http://forgerock.org/license/CDDLv1.0.html
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 */
package org.forgerock.maven.plugins.utils;

import org.forgerock.maven.plugins.LinkTester;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Peter Major
 */
public class MyErrorHandler implements ErrorHandler {

    private LinkTester linkTester;

    public MyErrorHandler(LinkTester linkTester) {
        this.linkTester = linkTester;
    }

    public void warning(SAXParseException saxpe) throws SAXException {
        linkTester.warn("Warning while processing: " + linkTester.getCurrentPath() + "\n" + saxpe.getMessage());
    }

    public void error(SAXParseException saxpe) throws SAXException {
        linkTester.setFailure();
        linkTester.error("Error while processing: " + linkTester.getCurrentPath() + "\n" + saxpe.getMessage());
    }

    public void fatalError(SAXParseException saxpe) throws SAXException {
        linkTester.setFailure();
        linkTester.error("Fatal error while processing: " + linkTester.getCurrentPath() + "\n" + saxpe.getMessage());
    }
}