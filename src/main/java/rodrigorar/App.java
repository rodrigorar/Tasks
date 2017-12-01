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

package rodrigorar;

// import javax.swing.UIManager;
// import javax.swing.UnsupportedLookAndFeelException;

import rodrigorar.data.services.ServicesPersistence;
import rodrigorar.ui.mainwindow.Window;

public class App {

    public static void bootstrap() {
        System.out.println("Bootstrapping Application");
        ServicesPersistence.getInstance().loadAppConfigurations();

        // This is to set the look and feel, i might change it to GTK because
        // it is so much prettier.
        // try {
        //     UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        // } catch (UnsupportedLookAndFeelException
        //          | ClassNotFoundException
        //          | InstantiationException
        //          | IllegalAccessException exception) {
        //
        //     exception.printStackTrace();
        // }
    }

    public static void main( String[] args ) {
        bootstrap();
        Window window = new Window();
        window.setVisible(true);
    }
}
