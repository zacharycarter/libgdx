/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.tests.extensions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.tests.utils.GdxTest;

public class FreeTypeTest extends GdxTest {
	BitmapFont font;
	SpriteBatch batch;
	BitmapFont ftFont;

	@Override
	public void create () {
		boolean flip = false;
		batch = new SpriteBatch();
		if (flip) {
			OrthographicCamera cam = new OrthographicCamera();
			cam.setToOrtho(flip);
			cam.update();
			batch.setProjectionMatrix(cam.combined);
		}
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), flip);
		FileHandle fontFile = Gdx.files.internal("data/arial.ttf");
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
		FreeTypeBitmapFontData fontData = generator.generateData(15, FreeTypeFontGenerator.DEFAULT_CHARS, flip);
		ftFont = generator.generateFont(15, FreeTypeFontGenerator.DEFAULT_CHARS, flip);
		generator.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		font.setColor(Color.RED);
		font.drawMultiLine(batch, "This is a test\nAnd another line\n()Â§$%&/!12390#", 100, 112);
		ftFont.drawMultiLine(batch, "This is a test\nAnd another line\n()Â§$%&/!12390#", 100, 112);
// batch.disableBlending();
		batch.draw(ftFont.getRegion(), 300, 0);
// batch.enableBlending();
		batch.end();
	}

	@Override
	public boolean needsGL20 () {
		return true;
	}
}
