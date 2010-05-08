/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package noop.graph;

import noop.model.LanguageElement;
import noop.model.Library;

/**
 * Creates the vertex in the element graph for each element encountered which doesn't have one already.
 * @author alexeagle@google.com (Alex Eagle)
 */
public class VertexCreatingVisitor extends ModelVisitor {
  private Library currentLibrary;

  @Override
  public void enter(LanguageElement element) {
    if (element instanceof Library) {
      currentLibrary = (Library) element;
    }
    if (currentLibrary != null) {
      if (element.vertex == Vertex.NONE) {
        int nextIndex = currentLibrary.add(element);
        element.vertex = new Vertex(currentLibrary.uid, nextIndex);
      }
    }
  }
}
