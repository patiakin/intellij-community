// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.wm;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.ui.Painter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface IdeGlassPane {
  void addMousePreprocessor(@NotNull MouseListener listener, @NotNull Disposable parent);
  void addMouseMotionPreprocessor(@NotNull MouseMotionListener listener, @NotNull Disposable parent);

  void addPainter(final Component component, @NotNull Painter painter, @NotNull Disposable parent);

  /**
   * @deprecated to remove the listener, invoke {@code Disposer.dispose(disposable);} on the disposable
   *  which you've passed to {@link #addMouseMotionPreprocessor(MouseMotionListener, Disposable)} instead
   */
  @Deprecated
  void removeMouseMotionPreprocessor(@NotNull MouseMotionListener listener);

  void setCursor(@Nullable Cursor cursor, @NotNull Object requestor);

  interface TopComponent {
    boolean canBePreprocessed(@NotNull MouseEvent e);
  }
}
