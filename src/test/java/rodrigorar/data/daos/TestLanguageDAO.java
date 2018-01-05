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

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.jdom2.Element;

import rodrigorar.data.daos.LanguageDAO;
import rodrigorar.domain.SupportedLanguages;
import rodrigorar.domain.pojos.Language;
import rodrigorar.utils.Constants.XMLLabels;

public class TestLanguageDAO {
    public static final String NAME = "English";
    public static final String SIMPLE_NAME = "EN";
    public static final String KEY_1 = "task";
    public static final String KEY_2 = "task_list";
    public static final String KEY_3 = "application";
    public static final String VALUE_1 = "Task";
    public static final String VALUE_2 = "Task List";
    public static final String VALUE_3 = "Application";

    @Test
    public void testConvertToObject() {
        Element languageElement = new Element(XMLLabels.LANGUAGE);

        Element nameElement = new Element(XMLLabels.NAME);
        nameElement.setText(NAME);

        Element simpleNameElement = new Element(XMLLabels.SIMPLE_NAME);
        simpleNameElement.setText(SIMPLE_NAME);

        Element translationsElement = new Element(XMLLabels.TRANSLATIONS);

        Element translation1 = new Element(XMLLabels.TRANSLATION);
        Element key1 = new Element(XMLLabels.KEY);
        key1.setText(KEY_1);
        Element value1 = new Element(XMLLabels.VALUE);
        value1.setText(VALUE_1);
        translation1.addContent(key1);
        translation1.addContent(value1);

        Element translation2 = new Element(XMLLabels.TRANSLATION);
        Element key2 = new Element(XMLLabels.KEY);
        key2.setText(KEY_2);
        Element value2 = new Element(XMLLabels.VALUE);
        value2.setText(VALUE_2);
        translation2.addContent(key2);
        translation2.addContent(value2);

        Element translation3 = new Element(XMLLabels.TRANSLATION);
        Element key3 = new Element(XMLLabels.KEY);
        key3.setText(KEY_3);
        Element value3 = new Element(XMLLabels.VALUE);
        value3.setText(VALUE_3);
        translation3.addContent(key3);
        translation3.addContent(value3);

        translationsElement.addContent(translation1);
        translationsElement.addContent(translation2);
        translationsElement.addContent(translation3);

        languageElement.addContent(nameElement);
        languageElement.addContent(simpleNameElement);
        languageElement.addContent(translationsElement);

        LanguageDAO dao = new LanguageDAO();

        Language language = dao.convertToObject(languageElement);

        assertEquals(language.getName(), NAME);
        assertEquals(language.getSimpleName(), SIMPLE_NAME);
        assertEquals(language.getTranslation(KEY_1), VALUE_1);
        assertEquals(language.getTranslation(KEY_2), VALUE_2);
        assertEquals(language.getTranslation(KEY_3), VALUE_3);
    }

    @Test
    public void testLoad() {
        LanguageDAO dao = new LanguageDAO();

        Language ptLanguage = dao.load(SupportedLanguages.Languages.PT);
        assertEquals(ptLanguage.getName(), "Portuguese");

        Language enLanguage = dao.load(SupportedLanguages.Languages.EN);
        assertEquals(enLanguage.getName(), "English");
    }
}
