const data = {
  "blocks": [
    {
      "type": "header",
      "data": {
        "text": "Editor.js",
        "level": 2
      }
    },
    {
      "type" : "paragraph",
      "data" : {
        "text" : "Hey. Meet the new Editor. On this page you can see it in action — try to edit this text. Source code of the page contains the example of connection and configuration."
      }
    },
    {
      "type": "header",
      "data": {
        "text": "Key features",
        "level": 3
      }
    },
    {
      "type" : "list",
      "data" : {
        "style" : "unordered",
        "items" : [
          {
            "content": "Apples",
            "items": [
              {
                "content": "Red",
                "items": []
              },
              {
                "content": "Green",
                "items": []
              },
            ]
          },
          {
            "content": "Bananas",
            "items": [
              {
                "content": "Yellow",
                "items": []
              },
            ]
          }
        ]
      }
    },
    {
      "type" : "paragraph",
      "data" : {
        "text" : 'Create a directory for your module, enter it and run <code class="inline-code">npm init</code> command.'
      }
    },
    {
      "type" : "quote",
      "data" : {
        "text" : "We have been working on this project more than three years. Several large media projects help us to test and debug the Editor, to make its core more stable. At the same time we significantly improved the API. Now, it can be used to create any plugin for any task. Hope you enjoy. 😏",
        "caption" : "What does it mean «block-styled editor»",
        "alignment" : "left"
      }
    },
    {
      "type" : "table",
      "data" : {
        "withHeadings": true,
        "content" : [ [ "Kine", "Pigs", "Chicken" ], [ "1 pcs", "3 pcs", "12 pcs" ], [ "100$", "200$", "150$" ] ]
      }
    },
    {
      "type" : "code",
      "data" : {
        "code": "* {\n  margin: 0;\n  padding: 0;\n  box-sizing: border-box;\n  font-family: 'Fira Sans', sans-serif;\n  &::selection {\n    background: transparentize(#42B883, 0.5);\n  }\n}"
      }
    },
    {
      "type" : "delimiter",
      "data" : {}
    },

    {
      "type": "image",
      "data": {
        "file": {
          "url" : "/logo.png",
          "size": 18899,
          "name": "logo.png",
          "extension": "png"
        },
        "caption": "로그 이미지 입니다.",
        "stretched": false,
        "withBorder": false,
        "withBackground": true
      }
    },
    {
      "type" : "attaches",
      "data" : {
        "file": {
          "url" : "/logo.png",
          "size": 18899,
          "name": "logo.png",
          "extension": "png"
        },
        "title": "Logo"
      }
    }
  ]
}

export default data