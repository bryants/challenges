languages = ['french', 'portuguese', 'english', 'german', 'spanish'];

var diacritics = {
  french : /[àâèéêïôûç]/gi,
  portuguese : /[àáâãéêíóôõúç]/gi,
  german : /[äöüß]/gi,
  spanish : /[áéíóúñü]/gi,
  english : null
};

var words = {
  english : /\b(the|of|and|a|to)\b/gi,
  german : /\b(der|die|und|in|den)\b/gi,
  spanish : /\b(el|la|de|que|y)\b/gi,
  portuguese : /\b(o|a|de|da|e)\b/gi,
  french : /\b(de|la|le|et|les)\b/gi
};

function detectLanguage(text) {
  var scores = {};

  languages.forEach(function(language) {
    var diacriticMatches = text.match(diacritics[language]);
    var wordMatches = text.match(words[language]);
    scores[language] = (diacriticMatches ? diacriticMatches.length : 0) +
    (wordMatches ? wordMatches.length : 0);
  });

  return languages.sort(function(a, b) {
    return scores[a] > scores[b] ? -1 : 1;
  })[0];
}
