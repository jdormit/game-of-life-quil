# Game of Life with [Quil](http://quil.info)
> Conway's Game of Life implemented in ClojureScript

Conway's Game of Life is a cellular automaton created by the mathematician John Conway. It has four simple rules:

  1. Any cell with fewer than two neighbors dies
  2. Any cell with two or three neighbors lives
  3. Any cell with more than three neighbors dies
  4. Any unpopulated cell with exactly three neighbors becomes populated
  
For more information on the Game of Life, check out the [Wikipedia article](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

[Quil](http://quil.info) is a Clojure/ClojureScript library for creating interactive art. It is based on the [Processing language](https://processing.org).

## Usage

Run `lein compile` command and open `index.html` in your browser.

For interactive development run `lein cljsbuild auto` command. This command will be recompiling cljs to js each time you modify `core.cljs` and you can see result immediately by refreshing page.

The game starts in a paused state with no cells. Click on a cell to populate it. Click on a populated cell to depopulate it. When you have configured the game you way you want, press the play button and see what happens! The slider controls game speed.

## License
Copyright 2018 Jeremy Dormitzer

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
