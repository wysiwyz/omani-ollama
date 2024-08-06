## Ollama

1. 如果你想要使用 private LLM，不想把私人資料分享給公開的 GPT, Gemini, OpenAI
2. 呼叫公開的 LLM 很花錢

## Steps

1. 安裝 ollama 3.1

```
ollama run llama3.1:8b
```
Note: 有料的 web ui

https://github.com/open-webui/open-webui

2. 建立 springboot project

![image-20240806140229158](/src/main/resources/static/img/img.png)

3. 移除版本
   - `<spring-ai.version>1.0.0-M1</spring-ai.version>` 改成 SNAPSHOT
