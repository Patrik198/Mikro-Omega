# 🎮 Minecart Catcher

Jednoduchá 2D hra v Javě, kde ovládáš vozík a chytáš padající předměty.

---

## 📋 Popis hry

Hráč ovládá minecart pohybující se po spodní části obrazovky. Shora padají různé předměty — cenné je potřeba chytit, nebezpečné se vyhnout. Čím více předmětů chytíš, tím rychleji padají.

---

## 🕹️ Ovládání

| Klávesa | Akce |
|--------|------|
| `A` | Pohyb doleva |
| `D` | Pohyb doprava |

---

## 📦 Předměty

| Obrázek | Předmět | Efekt |
|---------|---------|-------|
| 💎 | Diamant | +1 bod |
| 🪨 | Železný ingot | +1 bod |
| 🥇 | Zlato | +1 bod |
| 🍶 | Lahev | -1 život |

---

## ❤️ Životy

Hráč začíná se **3 životy**. Každé chycení lahve ubere jeden život. Při ztrátě všech životů hra končí.

---

## 📈 Obtížnost

Každých **10 chycených předmětů** se zvýší rychlost padání.

---

## 🏆 Highscore

Nejvyšší dosažené skóre se automaticky ukládá do souboru `highscore.txt` a načítá při každém spuštění hry.

---

## 🚀 Spuštění

### Požadavky
- Java 8 nebo novější

### Postup
1. Stáhni nebo naklonuj repozitář
2. Zkompiluj projekt
3. Spusť třídu `Main`
