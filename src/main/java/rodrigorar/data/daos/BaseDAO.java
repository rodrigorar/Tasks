/******************************************************************************
* Copyright 2017 Rodrigo Ramos Rosa
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/

package rodrigorar.data.daos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public abstract class BaseDAO<T> {

    protected Element getRootElement(String filepath) {
        Element rootElement = null;

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(filepath);
            Document document = (Document)builder.build(xmlFile);
            rootElement = document.getRootElement();
        } catch (IOException | JDOMException exception) {
            exception.printStackTrace();
        }

        return rootElement;
    }

    protected void writeToFile(Element rootElement, String filepath) {
        try {
            Document document = new Document(rootElement);
            XMLOutputter outputter = new XMLOutputter();

            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter(filepath));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public abstract T convertToObject(Element dataElement);

    public abstract Element convertToElement(T dataObject);
}
