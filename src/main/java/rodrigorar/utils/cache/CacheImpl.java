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

package rodrigorar.utils.cache;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class CacheImpl<T, U>
implements
Cache<T, U> {
    private Map<T, U> _cachedItems;

    public CacheImpl() {
        _cachedItems = new HashMap<T, U>();
    }     
    
    public void addItem(T itemKey, U item) {
        _cachedItems.put(itemKey, item);
    }

    public U getItem(T itemKey) {
        U rValue = null;

        if (_cachedItems.containsKey(itemKey)) {
            rValue = _cachedItems.get(itemKey);
        }
    
        return rValue;
    }

    public List<U> getAllItems() {
        List<U> itemList = new LinkedList<U>();

        Set<T> keySet = _cachedItems.keySet();
        for (T key : keySet) {
            U item = _cachedItems.get(key);
            itemList.add(item);
        }

        return itemList;
    }
}
